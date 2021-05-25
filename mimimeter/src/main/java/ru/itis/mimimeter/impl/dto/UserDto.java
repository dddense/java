package ru.itis.mimimeter.impl.dto;

import lombok.*;
import ru.itis.mimimeter.impl.models.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {

    private Long id;
    private String username;

    public static UserDto from(User user) {

        return UserDto.builder()
                .username(user.getUsername())
                .id(user.getId())
                .build();
    }

    public static List<UserDto> from(List<User> users) {

        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }

    public static Set<UserDto> from(Set<User> users) {

        return users.stream().map(UserDto::from).collect(Collectors.toSet());
    }
}
