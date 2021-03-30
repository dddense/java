package ru.itis.javalab.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.models.League;
import ru.itis.javalab.repositories.old.LeaguesRepository;

import java.util.List;
import java.util.Optional;


@Service
public class LeagueServiceImpl implements LeagueService {

    private LeaguesRepository leaguesRepository;

    public LeagueServiceImpl(LeaguesRepository leaguesRepository) {

        this.leaguesRepository = leaguesRepository;
    }

    @Override
    public List<League> getAll() {
        return leaguesRepository.findAll();
    }

    @Override
    public Optional<League> getById(int id) {

        return leaguesRepository.findById(id);
    }

    @Override
    public Optional<League> getByName(String name) {

        return leaguesRepository.findByName(name);
    }
}
