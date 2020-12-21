package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.List;

public interface UsersService {

    List<User> getAllUsers();

    List<User> getUsersByAge(int age);

    List<User> getUserByUsername(String username);

    void addUser(User user);

    String setPassword(String password);

    boolean matches(String password, String hash);
}
