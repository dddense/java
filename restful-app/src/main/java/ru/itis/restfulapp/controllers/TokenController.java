package ru.itis.restfulapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.restfulapp.dto.TokenDto;
import ru.itis.restfulapp.models.Token;
import ru.itis.restfulapp.models.User;
import ru.itis.restfulapp.redis.services.RedisUsersService;
import ru.itis.restfulapp.repositories.UsersRepository;
import ru.itis.restfulapp.utils.TokenProvider;

@RestController
public class TokenController {

    private TokenProvider provider;

    private RedisUsersService redisUsersService;

    @Autowired
    public TokenController(TokenProvider provider, RedisUsersService redisUsersService) {

        this.provider = provider;
        this.redisUsersService = redisUsersService;
    }

    @PostMapping("/refresh/{user-id}")
    public ResponseEntity<TokenDto> updateTokens(@PathVariable("user-id") Long id) {

        String access = redisUsersService.updateUserTokens(id);

        return ResponseEntity.ok(TokenDto.builder()
                .token(access)
                .build());
    }
}
