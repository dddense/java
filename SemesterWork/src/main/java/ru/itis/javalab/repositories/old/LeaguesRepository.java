package ru.itis.javalab.repositories.old;

import ru.itis.javalab.models.League;

import java.util.Optional;

public interface LeaguesRepository extends CrudRepository<League> {

    Optional<League> findByName(String name);
}
