package ru.itis.javalab.services;

import ru.itis.javalab.models.Team;
import ru.itis.javalab.repositories.TeamsRepository;

import java.util.List;

public class TeamsServiceImpl implements TeamsService {

    private TeamsRepository teamsRepository;

    public TeamsServiceImpl(TeamsRepository teamsRepository) {

        this.teamsRepository = teamsRepository;
    }

    @Override
    public List<Team> getAllByLeagueId(int id) {

        return teamsRepository.findAllByLeagueId(id);
    }

    @Override
    public List<Team> getALl() {

        return teamsRepository.findAll();
    }

    @Override
    public Team getByName(String name) {

        return teamsRepository.findByName(name);
    }
}
