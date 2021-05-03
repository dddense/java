package ru.itis.semestralwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.semestralwork.models.ConfirmationToken;
import ru.itis.semestralwork.models.User;
import ru.itis.semestralwork.repositories.ConfirmationTokensRepository;
import ru.itis.semestralwork.repositories.UsersRepository;

import java.util.Optional;

@Service
public class ConfirmationTokensServiceImpl implements ConfirmationTokensService {

    @Autowired
    private ConfirmationTokensRepository confirmationTokensRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SmsService smsService;

    @Override
    public Optional<User> confirmUserByToken(String token) {

        ConfirmationToken cToken = getByToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
        User user = usersRepository.findById(cToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));

        if (user.getState().equals(User.State.NOT_CONFIRMED)) {
            user.setState(User.State.ACTIVE);
            usersRepository.save(user);
            smsService.sendMessage(user.getPhone(), "Your account is successfully verified");
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
