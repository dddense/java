package ru.itis.restfulapp.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.restfulapp.models.Token;
import ru.itis.restfulapp.models.User;
import ru.itis.restfulapp.repositories.TokensRepository;
import ru.itis.restfulapp.repositories.UsersRepository;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.function.Supplier;

@Component
public class AccessTokenAuthenticationFilter extends OncePerRequestFilter {

    @Value("${token.secret}")
    private String secretWord;

    @Value("${token.access.expiration}")
    private Long accessExp;

    @Autowired
    private TokensRepository tokensRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws IOException, ServletException {

        String accessToken = request.getHeader("access-token");

        if (accessToken != null) {
            DecodedJWT aToken = JWT.require(Algorithm.HMAC256(secretWord))
                    .build()
                    .verify(accessToken);

            Long userId = Long.parseLong(aToken.getSubject());
            Date expiresAt = aToken.getExpiresAt();
            Date currentDate = new Date();

            if (currentDate.getTime() < expiresAt.getTime()) { //access invalid
                response.addHeader("access-status", "invalid");
                try {

                    Token refreshToken = tokensRepository
                            .findFirstByUserId(userId)
                            .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("Not found"));

                    DecodedJWT decodedRefreshToken = JWT.require(Algorithm.HMAC256(secretWord))
                            .build()
                            .verify(refreshToken.getToken());

                    Date expires = decodedRefreshToken.getExpiresAt();

                    if (expires.getTime() < currentDate.getTime()) { //refresh invalid
                        response.sendRedirect("/login");
                    } else { //refresh valid

                        User user = usersRepository
                                .findById(userId)
                                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("Not found"));

                        Date aExp = new Date();

                        aExp.setTime(System.currentTimeMillis() + accessExp);

                        String access = JWT.create()
                                .withSubject(user.getId().toString())
                                .withClaim("role", String.valueOf(user.getRole()))
                                .withExpiresAt(aExp)
                                .sign(Algorithm.HMAC256(secretWord));

                        response.addHeader("access-token", access);

                        TokenAuthentication tokenAuthentication = new TokenAuthentication(refreshToken.getToken());

                        SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
                    }
                } catch (Throwable throwable) {
                    throw new IllegalStateException(throwable);
                }
            } else { //access valid
                response.addHeader("access-token", accessToken);
                response.addHeader("access-status", "valid");

                try {

                    Token refreshToken = tokensRepository
                            .findFirstByUserId(userId)
                            .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("Not found"));

                    TokenAuthentication tokenAuthentication = new TokenAuthentication(refreshToken.getToken());

                    SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);

                } catch (Throwable throwable) {
                    throw new IllegalStateException(throwable);
                }
            }
        }


        filterChain.doFilter(request, response);
    }
}