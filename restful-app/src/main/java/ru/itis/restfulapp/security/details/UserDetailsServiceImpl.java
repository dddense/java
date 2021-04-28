package ru.itis.restfulapp.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.restfulapp.models.Token;
import ru.itis.restfulapp.repositories.TokensRepository;

import java.util.function.Supplier;

@Service("tokenUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TokensRepository tokensRepository;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

        Token result;
        try {
            result = tokensRepository.
                    findByToken(token)
                    .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("Token not found"));
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }

        return new UserDetailsImpl(result.getUser());
    }
}
