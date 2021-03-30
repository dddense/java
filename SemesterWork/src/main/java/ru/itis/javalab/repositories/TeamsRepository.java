package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.Team;

import java.util.Optional;

public interface TeamsRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByName(String name);
}
