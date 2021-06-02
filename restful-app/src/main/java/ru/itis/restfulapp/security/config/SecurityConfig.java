package ru.itis.restfulapp.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import ru.itis.restfulapp.security.token.AccessTokenAuthenticationFilter;
import ru.itis.restfulapp.security.token.RefreshTokenAuthenticationFilter;
import ru.itis.restfulapp.security.token.TokenAuthenticationProvider;
import ru.itis.restfulapp.security.token.TokenLogoutFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AccessTokenAuthenticationFilter accessTokenAuthenticationFilter;

    private TokenAuthenticationProvider tokenAuthenticationProvider;

    private TokenLogoutFilter tokenLogoutFilter;

    private RefreshTokenAuthenticationFilter refreshTokenAuthenticationFilter;

    @Autowired
    public SecurityConfig(AccessTokenAuthenticationFilter accessTokenAuthenticationFilter,
                          TokenAuthenticationProvider tokenAuthenticationProvider,
                          TokenLogoutFilter tokenLogoutFilter,
                          RefreshTokenAuthenticationFilter refreshTokenAuthenticationFilter) {

        this.accessTokenAuthenticationFilter = accessTokenAuthenticationFilter;
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
        this.tokenLogoutFilter = tokenLogoutFilter;
        this.refreshTokenAuthenticationFilter = refreshTokenAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .addFilterAt(tokenLogoutFilter, LogoutFilter.class)
                .addFilterBefore(refreshTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(accessTokenAuthenticationFilter, RefreshTokenAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/teachers").hasAuthority("ADMIN")
                .antMatchers("/courses").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").hasAnyAuthority()
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
