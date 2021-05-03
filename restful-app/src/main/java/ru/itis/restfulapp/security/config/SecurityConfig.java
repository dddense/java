package ru.itis.restfulapp.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.restfulapp.security.token.AccessTokenAuthenticationFilter;
import ru.itis.restfulapp.security.token.RefreshTokenAuthenticationFilter;
import ru.itis.restfulapp.security.token.TokenAuthenticationProvider;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessTokenAuthenticationFilter accessTokenAuthenticationFilter;

    @Autowired
    private RefreshTokenAuthenticationFilter refreshTokenAuthenticationFilter;

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .addFilterBefore(accessTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(refreshTokenAuthenticationFilter, AccessTokenAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/teachers").hasAuthority("ADMIN")
                .antMatchers("/courses").permitAll()
                .antMatchers("/login").permitAll()
                .and()
                .sessionManagement().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(tokenAuthenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }
}
