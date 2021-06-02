package ru.itis.restfulapp.redis.repositories;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.restfulapp.redis.models.RedisUser;

public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {
}
