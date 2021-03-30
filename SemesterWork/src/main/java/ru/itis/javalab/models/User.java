package ru.itis.javalab.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;


    private enum Role {
        ADMIN, DEFAULT
    }

    private String confirmCode;

    public enum State {
        ACTIVE, BANNED
    }

    public boolean isActive() {

        return this.state == State.ACTIVE;
    }

    public boolean isBanned() {

        return !isActive();
    }

    public boolean isAdmin() {

        return this.role == Role.ADMIN;
    }
}
