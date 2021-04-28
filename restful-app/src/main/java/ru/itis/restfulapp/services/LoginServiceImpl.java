package ru.itis.restfulapp.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.restfulapp.dto.EmailPasswordDto;
import ru.itis.restfulapp.dto.TokenDto;
import ru.itis.restfulapp.models.Token;
import ru.itis.restfulapp.models.User;
import ru.itis.restfulapp.repositories.TokensRepository;
import ru.itis.restfulapp.repositories.UsersRepository;

import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokensRepository tokensRepository;

    @Value("${token.secret}")
    private String secretWord;

    @Value("${token.access.expiration}")
    private Long accessExpiration;

    @Value("${token.refresh.expiration}")
    private Long refreshExpiration;

    @Value("${token.maximum-for-user}")
    private Integer maxAmount;

    @Override
    public TokenDto login(EmailPasswordDto emailPassword) {

        User user;
        try {
            user = usersRepository.findByEmail(emailPassword.getEmail()).orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }

        if (passwordEncoder.matches(emailPassword.getPassword(), user.getPassword())) {

            Date accessExp = new Date();
            accessExp.setTime(System.currentTimeMillis() + accessExpiration);

            Date refreshExp = new Date();
            refreshExp.setTime(System.currentTimeMillis() + refreshExpiration);

            String access = JWT.create()
                    .withSubject(user.getId().toString())
                    .withClaim("role", String.valueOf(user.getRole()))
                    .withExpiresAt(accessExp)
                    .sign(Algorithm.HMAC256(secretWord));

            String refresh = JWT.create()
                    .withSubject(user.getId().toString())
                    .withExpiresAt(refreshExp)
                    .sign(Algorithm.HMAC256(secretWord));

            List<Token> tokenList = tokensRepository.findAllByUser(user);

            if (tokenList.size() == maxAmount) {
                tokensRepository.delete(tokenList.get(0));
            }

            tokensRepository.save(Token.builder()
                    .token(refresh)
                    .user(user)
                    .build());

            return TokenDto.builder().token(access).build();
        } else {
            throw new UsernameNotFoundException("Invalid name or password");
        }
    }
}