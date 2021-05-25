package ru.itis.mimimeter.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.mimimeter.impl.dto.*;
import ru.itis.mimimeter.impl.models.ConfirmationToken;
import ru.itis.mimimeter.impl.models.User;
import ru.itis.mimimeter.impl.repositories.ConfirmationTokensRepository;
import ru.itis.mimimeter.impl.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import static ru.itis.mimimeter.impl.dto.UserDto.from;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    private ConfirmationTokensRepository confirmationTokensRepository;

    private PasswordEncoder passwordEncoder;

    private MailsService mailsService;

    private ExecutorService executorService;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository,
                            ConfirmationTokensRepository confirmationTokensRepository,
                            PasswordEncoder passwordEncoder,
                            MailsService mailsService,
                            ExecutorService executorService
                            ) {

        this.confirmationTokensRepository = confirmationTokensRepository;
        this.executorService = executorService;
        this.mailsService = mailsService;
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDto addUser(UserForm form) {

        User user = User.builder()
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .username(form.getUsername())
                .phone(form.getPhone())
                .build();

        usersRepository.save(user);
        String code = UUID.randomUUID().toString();

        confirmationTokensRepository.save(ConfirmationToken
                .builder()
                .confirmationToken(code)
                .user(user)
                .build());

        executorService.submit(() -> mailsService.sendEmail(form.getEmail(), code));

        return from(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {

        return usersRepository.findById(id);
    }

    @Override
    public List<UserDto> getAllUsersDto() {

        return from(usersRepository.findAll());
    }

    @Override
    public List<User> getAllUsers() {

        return usersRepository.findAll();
    }

    @Override
    public UserDto getById(Long id) {

        return from(usersRepository.findById(id).orElseThrow(IllegalStateException::new));
    }

}