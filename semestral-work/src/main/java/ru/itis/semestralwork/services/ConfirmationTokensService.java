package ru.itis.semestralwork.services;

import ru.itis.semestralwork.models.ConfirmationToken;
import ru.itis.semestralwork.models.User;

import java.util.Optional;

public interface ConfirmationTokensService {

    Optional<User> confirmUserByToken(String token);

    Optional<ConfirmationToken> getByToken(String token);
}
