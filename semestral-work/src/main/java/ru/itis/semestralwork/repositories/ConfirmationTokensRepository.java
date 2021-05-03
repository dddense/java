package ru.itis.semestralwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.semestralwork.models.ConfirmationToken;
import ru.itis.semestralwork.models.User;

import java.util.Optional;

public interface ConfirmationTokensRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<User> findUserByConfirmationToken(String token);

    Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);
}
