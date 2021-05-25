package ru.itis.mimimeter.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.mimimeter.impl.models.ConfirmationToken;
import ru.itis.mimimeter.impl.models.User;
import ru.itis.mimimeter.impl.repositories.ConfirmationTokensRepository;
import ru.itis.mimimeter.impl.repositories.UsersRepository;

import java.util.Optional;

@Service
public class ConfirmationTokensServiceImpl implements ConfirmationTokensService {

    private ConfirmationTokensRepository confirmationTokensRepository;

    private UsersRepository usersRepository;

    @Autowired
    public ConfirmationTokensServiceImpl(UsersRepository usersRepository,
                                         ConfirmationTokensRepository confirmationTokensRepository) {

        this.confirmationTokensRepository = confirmationTokensRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<User> confirmUserByToken(String token) {

        System.out.println(token);
        ConfirmationToken cToken = getByToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
        User user = usersRepository.findById(cToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));

        if (user.getState().equals(User.State.NOT_CONFIRMED)) {
            user.setState(User.State.ACTIVE);
            usersRepository.save(user);
            return Optional.of(user);
        } else {
            throw new UsernameNotFoundException("User is already confirmed or banned");
        }
    }

    @Override
    public Optional<ConfirmationToken> getByToken(String token) {

        return confirmationTokensRepository.findConfirmationTokenByConfirmationToken(token);
    }
}
