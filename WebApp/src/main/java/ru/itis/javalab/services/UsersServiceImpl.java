package ru.itis.javalab.services;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {

        this.usersRepository = usersRepository;
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

}
