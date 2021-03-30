package ru.itis.javalab.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
@Entity
@Table
public class Team {

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
