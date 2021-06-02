package ru.itis.restfulapp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.restfulapp.models.Token;
import ru.itis.restfulapp.models.User;
import ru.itis.restfulapp.redis.models.RedisUser;
import ru.itis.restfulapp.redis.repositories.RedisUsersRepository;
import ru.itis.restfulapp.repositories.TokensRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class TokenProvider {

    private RedisUsersRepository redisUsersRepository;

    @Autowired
    public TokenProvider(RedisUsersRepository redisUsersRepository) {

        this.redisUsersRepository = redisUsersRepository;
    }

    @Value("${token.maximum-for-user}")
    private Integer maxAmount;

    @Value("${token.secret}")
    private String secretWord;

    @Value("${token.access.expiration}")
    private Long accessExpiration;

    @Value("${token.refresh.expiration}")
    private Long refreshExpiration;

    public String getAccessToken(User user) {

        Date accessExp = new Date();
        accessExp.setTime(System.currentTimeMillis() + accessExpiration);

        String access = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("redisId", user.getRedisId())
                .withClaim("role", String.valueOf(user.getRole()))
                .withExpiresAt(accessExp)
                .sign(Algorithm.HMAC256(secretWord));

        return access;
    }

    public String getRefreshToken(User user) {

        Date refreshExp = new Date();
        refreshExp.setTime(System.currentTimeMillis() + refreshExpiration);

        String refresh = JWT.create()
                .withSubject(user.getId().toString())
                .withExpiresAt(refreshExp)
                .sign(Algorithm.HMAC256(secretWord));

        return refresh;
    }

    public Token getUserRefresh(String token) {

        RedisUser redisUser = redisUsersRepository
                .findById(getClaim("redisId", token).asString())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return Token.builder()
                .token(redisUser.getTokens().get(0))
                .build();
    }

    public boolean valid(String token) {

        Long currentDate = new Date().getTime();
        Long expires = decode(token).getExpiresAt().getTime();

        return currentDate < expires;
    }

    public DecodedJWT decode(String token) {

        return JWT.require(Algorithm.HMAC256(secretWord))
                .build()
                .verify(token);
    }

    public Claim getClaim(String claim, String token) {

        Map<String, Claim> claims = decode(token).getClaims();

        return claims.get(claim);
    }
}
