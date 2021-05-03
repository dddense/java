package ru.itis.semestralwork.security.filters;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SmsFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpRequest request = (HttpRequest) servletRequest;

        if (request.getMethod().equals(HttpMethod.GET)) {

        }
    }
}
