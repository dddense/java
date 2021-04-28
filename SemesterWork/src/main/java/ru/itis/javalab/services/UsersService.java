package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.models.User;

import java.util.List;

public interface UsersService {

    List<User> getAllUsers();

    List<User> getUsersByAge(int age);

    List<User> getUserByUsername(String username);

    void addUser(UserForm form);

    String setPassword(String password);

    void banAll();

    boolean matches(String password, String hash);
}
