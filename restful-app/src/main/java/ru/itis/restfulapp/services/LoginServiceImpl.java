package ru.itis.restfulapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.restfulapp.dto.EmailPasswordDto;
import ru.itis.restfulapp.dto.TokenDto;
import ru.itis.restfulapp.models.Token;
import ru.itis.restfulapp.models.User;
import ru.itis.restfulapp.redis.services.RedisUsersService;
import ru.itis.restfulapp.repositories.TokensRepository;
import ru.itis.restfulapp.repositories.UsersRepository;
import ru.itis.restfulapp.utils.TokenProvider;

import java.util.function.Supplier;

@Service
public class LoginServiceImpl implements LoginService {

    private PasswordEncoder passwordEncoder;

    private UsersRepository usersRepository;

    private TokensRepository tokensRepository;

    private RedisUsersService redisUsersService;

    private TokenProvider tokenProvider;

    @Autowired
    public LoginServiceImpl(PasswordEncoder passwordEncoder,
                            UsersRepository usersRepository,
                            TokensRepository tokensRepository,
                            RedisUsersService redisUsersService,
                            TokenProvider tokenProvider) {

        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.tokensRepository = tokensRepository;
        this.redisUsersService = redisUsersService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public TokenDto login(EmailPasswordDto emailPassword) {

        User user;
        try {
            user = usersRepository.findByEmail(emailPassword.getEmail()).orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }

        if (passwordEncoder.matches(emailPassword.getPassword(), user.getPassword())) {

            Token token = Token.builder()
                    .token(tokenProvider.getRefreshToken(user))
                    .user(user)
                    .build();

//            tokensRepository.save(token);
            redisUsersService.addTokenToUser(user, token.getToken());

            return TokenDto.builder().token(tokenProvider.getAccessToken(user)).build();
        } else {
            throw new UsernameNotFoundException("Invalid name or password");
        }
    }
}