package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.utils.EmailUtil;
import ru.itis.javalab.utils.MailsGenerator;

import java.util.List;
import java.util.UUID;

@Service
@Profile("master")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${mail.subject}")
    private String subject;

    @Value("${mail.mail}")
    private String from;

    @Override
    public List<User> getAllUsers() {

        return usersRepository.findAll();
    }

    @Override
    public List<User> getUsersByAge(int age) {
        return null;
    }

    @Override
    public List<User> getUserByUsername(String username) {
        return null;
    }

    @Override
    public void addUser(UserForm form) {

        User user = User.builder()
                .email(form.getEmail())
                .password(setPassword(form.getPassword()))
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .confirmCode(UUID.randomUUID().toString())
                .state(User.State.ACTIVE)
                .build();

        usersRepository.save(user);

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, user.getConfirmCode());
        emailUtil.sendMail(user.getEmail(), from, subject, confirmMail);
    }

    @Override
    public String setPassword(String password) {

        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String password, String hash) {

        return passwordEncoder.matches(password, hash);
    }
}