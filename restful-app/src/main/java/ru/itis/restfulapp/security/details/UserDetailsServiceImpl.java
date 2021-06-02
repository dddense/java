package ru.itis.restfulapp.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.restfulapp.models.Token;
import ru.itis.restfulapp.models.User;
import ru.itis.restfulapp.redis.models.RedisUser;
import ru.itis.restfulapp.redis.repositories.RedisUsersRepository;
import ru.itis.restfulapp.redis.services.RedisUsersService;
import ru.itis.restfulapp.redis.services.RedisUsersServiceImpl;
import ru.itis.restfulapp.repositories.TokensRepository;
import ru.itis.restfulapp.repositories.UsersRepository;

import java.util.List;
import java.util.function.Supplier;

@Service("tokenUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsersRepository usersRepository;

    private RedisUsersRepository redisUsersRepository;

    @Autowired
    public UserDetailsServiceImpl(RedisUsersRepository redisUsersRepository, UsersRepository usersRepository) {

        this.redisUsersRepository = redisUsersRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String redisId) throws UsernameNotFoundException {

//        Token result;
        User user;
        try {
//            result = tokensRepository.
//                    findByToken(token)
//                    .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("Token not found"));
             RedisUser redisUser = redisUsersRepository.findById(redisId)
                     .orElseThrow(() -> new UsernameNotFoundException("User not found"));
             List<String> tokens = redisUser.getTokens();
             if (tokens != null && tokens.size() > 0) {
                 user = usersRepository.findById(redisUser.getUserId())
                         .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                 return new UserDetailsImpl(user);

             }
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }

//        return new UserDetailsImpl(result.getUser());
        return new UserDetailsImpl(new User());
    }
}
