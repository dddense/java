package ru.itis.mimimeter.impl.services;

import ru.itis.mimimeter.impl.models.ConfirmationToken;
import ru.itis.mimimeter.impl.models.User;

import java.util.Optional;

public interface ConfirmationTokensService {

    Optional<User> confirmUserByToken(String token);

    Optional<ConfirmationToken> getByToken(String token);
}
