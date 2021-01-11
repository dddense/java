package ru.itis.javalab.repositories;

import ru.itis.javalab.models.League;

import java.util.Optional;

public interface LeaguesRepository extends CrudRepository<League> {

    Optional<League> findByName(String name);
}
