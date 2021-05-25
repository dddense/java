package ru.itis.mimimeter.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.mimimeter.impl.models.ConfirmationToken;
import ru.itis.mimimeter.impl.models.User;

import java.util.Optional;

public interface ConfirmationTokensRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<User> findUserByConfirmationToken(String token);

    Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);
}
