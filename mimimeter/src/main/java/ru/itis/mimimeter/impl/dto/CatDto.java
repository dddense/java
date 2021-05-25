package ru.itis.mimimeter.impl.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import ru.itis.mimimeter.impl.models.Cat;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatDto implements Comparable<CatDto> {

    private Long id;
    private String name;
    private int votes;
    private String path;

    public static CatDto from(Cat cat) {

        return CatDto.builder()
                .id(cat.getId())
                .votes(cat.getVoters().size())
                .path(cat.getImagePath())
                .name(cat.getName()).build();
    }

    public static List<CatDto> from (List<Cat> cats) {

        return cats.stream().map(CatDto::from).collect(Collectors.toList());
    }

    @Override
    public int compareTo(@NotNull CatDto cat) {

        return cat.votes - this.votes;
    }
}
