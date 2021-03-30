package ru.itis.javalab.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.models.Team;
import ru.itis.javalab.repositories.TeamsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeamsServiceImpl implements TeamsService {

    private TeamsRepository teamsRepository;

    public TeamsServiceImpl(TeamsRepository teamsRepository) {

        this.teamsRepository = teamsRepository;
    }

    @Override
    public List<Team> getAllByLeagueId(int id) {

//        return teamsRepository.findAllByLeagueId(id);
        return null;
    }

    @Override
    public List<Team> getALl() {

        return teamsRepository.findAll();
    }

    @Override
    public Optional<Team> getByName(String name) {

        return teamsRepository.findByName(name);
    }

    @Override
    public Optional<Team> findById(Long id) {

        return teamsRepository.findById(id);
    }
}
