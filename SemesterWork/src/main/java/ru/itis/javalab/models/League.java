package ru.itis.javalab.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class League {

    private Integer id;
    private String name;
}
