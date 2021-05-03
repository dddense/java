package ru.itis.semestralwork.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @ManyToOne()
    @JoinColumn(name = "account_id")
    @ToString.Exclude
    private User account;
}
