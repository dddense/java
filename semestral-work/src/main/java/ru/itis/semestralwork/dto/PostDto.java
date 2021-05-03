package ru.itis.semestralwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.semestralwork.models.Post;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private Long id;
    private String author;
    private Long authorId;
    private String body;

    public static PostDto from(Post post) {

        return PostDto.builder()
                .author(post.getAccount().getUsername())
                .authorId(post.getAccount().getId())
                .id(post.getId())
                .body(post.getBody())
                .build();
    }

    public static List<PostDto> from(List<Post> postList) {

        return postList.stream().map(PostDto::from).collect(Collectors.toList());
    }
}
