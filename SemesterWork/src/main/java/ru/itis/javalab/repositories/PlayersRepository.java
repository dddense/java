package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Player;

import java.util.List;

public interface PlayersRepository extends CrudRepository<Player> {

    List<Player> findAllByTeamId(int id);

    List<Player> findAllByRole(int id);

    List<Player> findAllByAge(int age);

    List<Player> findAllByName(String name);

    List<Player> findAllByTeamName(String name);
}
