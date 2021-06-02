package ru.itis.restfulapp.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.restfulapp.models.Token;
import ru.itis.restfulapp.redis.models.RedisUser;
import ru.itis.restfulapp.redis.services.RedisUsersService;
import ru.itis.restfulapp.utils.TokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RefreshTokenAuthenticationFilter extends OncePerRequestFilter {

    private final RequestMatcher refreshRequest = new AntPathRequestMatcher("/refresh/**", "POST");

    private RedisUsersService redisUsersService;

    private TokenProvider provider;

    @Autowired
    public RefreshTokenAuthenticationFilter(RedisUsersService redisUsersService, TokenProvider provider) {

        this.redisUsersService = redisUsersService;
        this.provider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        if (refreshRequest.matches(httpServletRequest)) {
            String access = httpServletRequest.getHeader("access-token");
            if (access != null) {
                Token refresh = redisUsersService.getUserRefresh(access);
                httpServletRequest.setAttribute("refresh-status", provider.valid(refresh.getToken()));
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
