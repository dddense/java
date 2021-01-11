package ru.itis.javalab.services;

import ru.itis.javalab.models.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllByTeamId(int id);

    List<Player> getAllByRoleId(int id);

    List<Player> getAllByAge(int age);

    List<Player> getAllName(String name);

    List<Player> getAllByTeamName(String name);
}
