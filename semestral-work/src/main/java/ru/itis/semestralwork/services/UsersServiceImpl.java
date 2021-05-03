package ru.itis.semestralwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.semestralwork.dto.*;
import ru.itis.semestralwork.models.ConfirmationToken;
import ru.itis.semestralwork.models.Post;
import ru.itis.semestralwork.models.User;
import ru.itis.semestralwork.repositories.ConfirmationTokensRepository;
import ru.itis.semestralwork.repositories.PostsRepository;
import ru.itis.semestralwork.repositories.UsersRepository;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import static ru.itis.semestralwork.dto.UserDto.from;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private ConfirmationTokensRepository confirmationTokensRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailsService mailsService;

    @Autowired
    private ExecutorService executorService;

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

    @Override
    public UserDto addUserIntoSubscriptions(User mainUser, User userToAdd) {

        Set<Long> subscriptionsId = mainUser
                .getSubscriptions()
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet());

        if (subscriptionsId.contains(userToAdd.getId())) {
            return null;
        }

        mainUser.getSubscriptions().add(userToAdd);
        userToAdd.getSubscribers().add(mainUser);
        usersRepository.save(mainUser);
        usersRepository.save(userToAdd);

        return UserDto.from(mainUser);
    }

    @Override
    public List<PostDto> getPostsByUserId(Long id) {

        return PostDto.from(postsRepository.findByAccountId(id));
    }

    @Override
    public PostDto addPostByUser(User author, String body) {

        PostDto post = PostDto.from(postsRepository.save(Post.builder()
                .account(author)
                .body(body)
                .build()));

        return post;
    }

    @Override
    public UsersPage search(Integer size, Integer page, String query, String sortParam, String directionParam) {

        Sort.Direction dir = Sort.Direction.ASC;
        Sort sort = Sort.by(dir, "id");

        if (sortParam != null) {
            dir = Sort.Direction.fromString(directionParam);
        }

        if (sortParam != null) {
            sort = Sort.by(dir, sortParam);
        }

        if (query == null) {
            query = "empty";
        }

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<User> usersPage = usersRepository.search(query, pageRequest);

        return UsersPage.builder()
                .pagesCount(usersPage.getTotalPages())
                .users(UserDto.from(usersPage.getContent()))
                .build();
    }
}