package ru.itis.javalab.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Player {

    private String name;
    private Integer id;
    private Integer teamId;
    private Integer age;
    private Integer pos;
}
