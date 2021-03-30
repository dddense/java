package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.Player;

public interface PlayersRepository extends JpaRepository<Player, Long> {
}
