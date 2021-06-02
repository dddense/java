package ru.itis.restfulapp.redis.services;

import ru.itis.restfulapp.models.User;

public interface RedisUsersService {

    void addTokenToUser(User user, String token);

    void deleteToken(User user, String token);

    String updateUserTokens(Long userId);
}
