package ru.itis.mimimeter.impl.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String username;
    private String hashPassword;
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private State state = State.NOT_CONFIRMED;

    @OneToMany(mappedBy = "owner")
    private List<Cat> cats;

    @ManyToMany(mappedBy = "voters")
    private Set<Cat> votedCats;

    private boolean isDeleted;

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

    public enum Role {
        ADMIN, USER
    }

    public enum State {
        ACTIVE, BANNED, NOT_CONFIRMED
    }

    public boolean isActive() {

        return this.state == State.ACTIVE;
    }

    public boolean isBanned() {

        return this.state == State.BANNED;
    }

    public boolean isConfirmed() {

        return this.state == State.NOT_CONFIRMED;
    }

    public String toString() {

        return email;
    }
}
