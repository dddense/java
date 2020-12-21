package ru.itis.javalab.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {

        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {

        return usersRepository.findAll();
    }

    @Override
    public List<User> getUsersByAge(int age) {

        return usersRepository.findAllByAge(age);
    }

    @Override
    public List<User> getUserByUsername(String username) {

        return usersRepository.findByUsername(username);
    }

    @Override
    public void addUser(User user) {

        usersRepository.save(user);
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
