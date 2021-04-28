package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.utils.MailsGenerator;

import java.util.List;
import java.util.UUID;

@Service
@Profile("dev")
public class UsersServiceMockImpl implements UsersService {

    @Autowired
    private MailsGenerator mailsGenerator;

    @Value("${server.url}")
    private String serverUrl;

    @Override
    public List<User> getAllUsers() {
        return null;
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
                .confirmCode(UUID.randomUUID().toString())
                .build();

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, user.getConfirmCode());
        System.out.println(confirmMail);
    }

    @Override
    public String setPassword(String password) {
        return null;
    }

    @Override
    public void banAll() {

    }

    @Override
    public boolean matches(String password, String hash) {
        return false;
    }
}
