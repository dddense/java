package ru.itis.javalab.models;

import lombok.*;
import ru.itis.javalab.models.Team;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
@Entity
@Table
public class Player {

    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer age;
    @ManyToOne
    @JoinColumn(columnDefinition = "team_id")
    private Team team;
    private Integer pos;
}
