package ru.itis.semestralwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.semestralwork.models.Post;
import ru.itis.semestralwork.models.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private List<PostDto> posts;

    public static UserDto from(User user) {

        return UserDto.builder()
                .username(user.getUsername())
                .id(user.getId())
                .posts(PostDto.from(user.getPosts() == null ? new LinkedList<>() : user.getPosts()))
                .build();
    }

    public static List<UserDto> from(List<User> users) {

        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }

    public static Set<UserDto> from(Set<User> users) {

        return users.stream().map(UserDto::from).collect(Collectors.toSet());
    }
}
