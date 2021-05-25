package ru.itis.mimimeter.impl.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private User owner;

    @ManyToMany
    @JoinTable(name = "users_cats",
                joinColumns = @JoinColumn(name = "cat_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"))
    private Set<User> voters;
}
