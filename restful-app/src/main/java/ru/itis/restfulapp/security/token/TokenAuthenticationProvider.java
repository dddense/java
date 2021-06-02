package ru.itis.restfulapp.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.itis.restfulapp.security.details.UserDetailsServiceImpl;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;


    @Autowired
    public TokenAuthenticationProvider(@Qualifier("tokenUserDetailsService") UserDetailsServiceImpl userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        tokenAuthentication.setAuthenticated(true);
        tokenAuthentication.setUserDetails(userDetails);

        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {

        return TokenAuthentication.class.equals(aClass);
    }
}
