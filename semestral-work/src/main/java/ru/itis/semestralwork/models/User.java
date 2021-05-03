package ru.itis.semestralwork.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany()
    @JoinTable(name="subscription",
                joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="subscriber_id", referencedColumnName = "id"))
    private Set<User> subscriptions;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany()
    @JoinTable(name = "subscription",
                joinColumns = @JoinColumn(name="subscriber_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> subscribers;

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
