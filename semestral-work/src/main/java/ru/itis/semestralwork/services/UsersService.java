package ru.itis.semestralwork.services;

import ru.itis.semestralwork.dto.*;
import ru.itis.semestralwork.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    UserDto addUser(UserForm form);

    Optional<User> getUserById(Long id);

    List<UserDto> getAllUsersDto();

    List<User> getAllUsers();

    UserDto getById(Long id);

    UserDto addUserIntoSubscriptions(User mainUser, User userToAdd);

    List<PostDto> getPostsByUserId(Long id);

    PostDto addPostByUser(User author, String body);

    UsersPage search(Integer size, Integer page, String query, String sort, String direction);
}
