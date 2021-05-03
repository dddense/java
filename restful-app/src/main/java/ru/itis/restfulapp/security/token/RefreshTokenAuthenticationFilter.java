package ru.itis.restfulapp.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.restfulapp.repositories.TokensRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RefreshTokenAuthenticationFilter extends OncePerRequestFilter {

    @Value("${token.secret}")
    private String secretWord;

    @Value("${token.access.expiration}")
    private Long accessExp;

    @Autowired
    private TokensRepository tokensRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String accessToken = request.getHeader("access-token");
        String status = request.getHeader("access-status");

        filterChain.doFilter(request, response);
    }
}
