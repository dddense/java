package ru.itis.restfulapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.restfulapp.models.User;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
