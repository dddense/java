package ru.itis.javalab.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Team {

    private String name;
    private Integer id;
    private Integer leagueId;
}
