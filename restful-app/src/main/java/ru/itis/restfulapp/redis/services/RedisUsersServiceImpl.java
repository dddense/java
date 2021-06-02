package ru.itis.restfulapp.redis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.restfulapp.models.User;
import ru.itis.restfulapp.redis.models.RedisUser;
import ru.itis.restfulapp.redis.repositories.RedisUsersRepository;
import ru.itis.restfulapp.repositories.UsersRepository;
import ru.itis.restfulapp.utils.TokenProvider;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class RedisUsersServiceImpl implements RedisUsersService {

    private RedisUsersRepository redisUsersRepository;

    private UsersRepository usersRepository;

    private TokenProvider provider;

    @Autowired
    public RedisUsersServiceImpl(RedisUsersRepository redisUsersRepository,
                                 UsersRepository usersRepository,
                                 TokenProvider provider) {

        this.redisUsersRepository = redisUsersRepository;
        this.usersRepository = usersRepository;
        this.provider = provider;
    }

    @Override
    public void addTokenToUser(User user, String token) {

        String redisId = user.getRedisId();
        RedisUser redisUser;

        if (redisId != null) { //redis token exists
            redisUser = redisUsersRepository.findById(redisId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            List<String> tokens = redisUser.getTokens();
            if (tokens == null) {
                redisUser.setTokens(new LinkedList<>());
            }
            if (tokens.size() == 0) {
                redisUser.getTokens().add(token);
            } else {
                tokens.remove(tokens.get(0));
                tokens.add(token);
            }
        } else { //no redis token
            redisUser = RedisUser.builder()
                    .userId(user.getId())
                    .tokens(Collections.singletonList(token))
                    .build();
        }

        redisUsersRepository.save(redisUser);
        user.setRedisId(redisUser.getId());
        usersRepository.save(user);
    }

    @Override
    public void deleteToken(User user, String token) {

        RedisUser redisUser = redisUsersRepository.findById(user.getRedisId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        redisUser.getTokens().remove(token);
        redisUsersRepository.save(redisUser);
    }

    @Override
    public String updateUserTokens(Long userId) {

        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String access = provider.getAccessToken(user);
        String refresh = provider.getRefreshToken(user);
        addTokenToUser(user, refresh);

        return access;
    }
}
