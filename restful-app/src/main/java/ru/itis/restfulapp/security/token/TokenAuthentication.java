package ru.itis.restfulapp.security.token;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.restfulapp.security.details.UserDetailsImpl;

import java.util.Collection;

public class TokenAuthentication implements Authentication {

    private UserDetailsImpl userDetails;

    private boolean isAuthenticated;

    private String token;

    public TokenAuthentication(String token) {

        this.token = token;
    }

    public void setUserDetails(UserDetails userDetails) {

        this.userDetails = (UserDetailsImpl) userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {

        return userDetails.getPassword();
    }

    @Override
    public Object getDetails() {

        return userDetails;
    }

    @Override
    public Object getPrincipal() {

        if (userDetails != null) {
            return userDetails.getUser();
        } else {
            return null;
        }
    }

    @Override
    public boolean isAuthenticated() {

        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

        this.isAuthenticated = b;
    }

    @Override
    public String getName() {

        return token;
    }
}
