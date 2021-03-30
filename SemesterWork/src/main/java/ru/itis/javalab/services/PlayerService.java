package ru.itis.javalab.services;

import ru.itis.javalab.models.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    List<Player> getAll();

    List<Player> getAllByTeamId(int id);

    List<Player> getAllByRoleId(int id);

    List<Player> getAllByAge(int age);

    List<Player> getAllName(String name);

    List<Player> getAllByTeamName(String name);

    Optional<Player> getByName(String name);

    Optional<Player> getById(Long id);
}
