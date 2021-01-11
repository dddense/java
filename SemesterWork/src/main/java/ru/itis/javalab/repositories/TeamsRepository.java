package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Team;

import java.util.List;

public interface TeamsRepository extends CrudRepository<Team> {

    List<Team> findAllByLeagueId(int id);
    Team findByName(String name);
}
