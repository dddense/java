package ru.itis.javalab.services;

import ru.itis.javalab.models.League;

import java.util.List;
import java.util.Optional;

public interface LeagueService {

    List<League> getAll();

    Optional<League> getById(int id);

    Optional<League> getByName(String name);
}
