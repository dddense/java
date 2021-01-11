package ru.itis.javalab.services;

import ru.itis.javalab.models.Team;

import java.util.List;

public interface TeamsService {

    List<Team> getAllByLeagueId(int id);
    List<Team> getALl();
    Team getByName(String name);
    //TODO: find by id, name
}
