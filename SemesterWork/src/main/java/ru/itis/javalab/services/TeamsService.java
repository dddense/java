package ru.itis.javalab.services;

import ru.itis.javalab.models.Team;

import java.util.List;
import java.util.Optional;

public interface TeamsService {

    List<Team> getAllByLeagueId(int id);
    List<Team> getALl();
    Optional<Team> getByName(String name);
    Optional<Team> findById(Long id);
    //TODO: find by id, name
}
