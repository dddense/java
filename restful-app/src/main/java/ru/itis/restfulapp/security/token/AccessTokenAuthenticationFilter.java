package ru.itis.restfulapp.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.restfulapp.models.Token;
import ru.itis.restfulapp.models.User;
import ru.itis.restfulapp.redis.models.RedisUser;
import ru.itis.restfulapp.redis.repositories.RedisUsersRepository;
import ru.itis.restfulapp.repositories.TokensRepository;
import ru.itis.restfulapp.repositories.UsersRepository;
import ru.itis.restfulapp.utils.TokenProvider;

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
    public AccessTokenAuthenticationFilter(TokenProvider provider,
                                           TokensRepository tokensRepository,
                                           UsersRepository usersRepository,
                                           RedisUsersRepository redisUsersRepository) {

        this.provider = provider;
        this.tokensRepository = tokensRepository;
        this.usersRepository = usersRepository;
        this.redisUsersRepository = redisUsersRepository;
    }

    private TokenProvider provider;

    private TokensRepository tokensRepository;

    private UsersRepository usersRepository;

    private RedisUsersRepository redisUsersRepository;

    private final RequestMatcher excludedRequest = new AntPathRequestMatcher("/refresh/**", "POST");

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws IOException, ServletException {


        if (!excludedRequest.matches(request)) {
            String accessToken = request.getHeader("access-token");

            if (accessToken != null) {
                if (provider.valid(accessToken)) {
                    TokenAuthentication tokenAuthentication = new TokenAuthentication(provider.getClaim("redisId", accessToken).asString());
                    SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
                } else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.addHeader("user-id", provider.decode(accessToken).getSubject());
                }
            }
        }
//        if (accessToken != null) {
//            DecodedJWT aToken = JWT.require(Algorithm.HMAC256(secretWord))
//                    .build()
//                    .verify(accessToken);
//
//            Long userId = Long.parseLong(aToken.getSubject());
//            Date expiresAt = aToken.getExpiresAt();
//            Date currentDate = new Date();
//
//            if (currentDate.getTime() < expiresAt.getTime()) { //access invalid
//                try {
//
//                    Token refreshToken = tokensRepository
//                            .findFirstByUserId(userId)
//                            .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("Not found"));
//
//                    DecodedJWT decodedRefreshToken = JWT.require(Algorithm.HMAC256(secretWord))
//                            .build()
//                            .verify(refreshToken.getToken());
//
//                    Date expires = decodedRefreshToken.getExpiresAt();
//
//                    if (expires.getTime() < currentDate.getTime()) { //refresh invalid
//                        response.sendRedirect("/login");
//                    } else { //refresh valid
//
//                        User user = usersRepository
//                                .findById(userId)
//                                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("Not found"));
//
//                        Date aExp = new Date();
//
//                        aExp.setTime(System.currentTimeMillis() + accessExp);
//
//                        String access = JWT.create()
//                                .withSubject(user.getId().toString())
//                                .withClaim("role", String.valueOf(user.getRole()))
//                                .withExpiresAt(aExp)
//                                .sign(Algorithm.HMAC256(secretWord));
//
//                        response.addHeader("access-token", access);
//
//                        TokenAuthentication tokenAuthentication = new TokenAuthentication(refreshToken.getToken());
//
//                        SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
//                    }
//                } catch (Throwable throwable) {
//                    throw new IllegalStateException(throwable);
//                }
//            } else { //access valid
//                response.addHeader("access-token", accessToken);
//
//                try {
//
//                    Token refreshToken = tokensRepository
//                            .findFirstByUserId(userId)
//                            .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("Not found"));
//
//                    TokenAuthentication tokenAuthentication = new TokenAuthentication(refreshToken.getToken());
//
//                    SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
//
//                } catch (Throwable throwable) {
//                    throw new IllegalStateException(throwable);
//                }
//            }
//        }

        filterChain.doFilter(request, response);
    }
}