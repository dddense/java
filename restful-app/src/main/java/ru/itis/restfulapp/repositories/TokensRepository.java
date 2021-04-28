package ru.itis.restfulapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.restfulapp.models.Token;
import ru.itis.restfulapp.models.User;

import java.util.List;
import java.util.Optional;

public interface TokensRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    List<Token> findAllByUser(User user);

    Optional<Token> findFirstByUserId(Long id);
}
