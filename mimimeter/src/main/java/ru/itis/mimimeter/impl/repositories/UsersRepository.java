package ru.itis.mimimeter.impl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.mimimeter.impl.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}