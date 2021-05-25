package ru.itis.mimimeter.impl.services;

import ru.itis.mimimeter.impl.dto.*;
import ru.itis.mimimeter.impl.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    UserDto addUser(UserForm form);

    Optional<User> getUserById(Long id);

    List<UserDto> getAllUsersDto();

    List<User> getAllUsers();

    UserDto getById(Long id);
}
