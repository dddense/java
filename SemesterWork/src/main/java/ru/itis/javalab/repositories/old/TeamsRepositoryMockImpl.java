package ru.itis.javalab.repositories.old;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.Team;

import java.util.List;
import java.util.Optional;

@Profile("dev")
@Repository
public class TeamsRepositoryMockImpl implements TeamsRepository {
    @Override
    public List<Team> findAllByLeagueId(int id) {
        return null;
    }

    @Override
    public Team findByName(String name) {
        return null;
    }

    @Override
    public List<Team> findAll() {
        return null;
    }

    @Override
    public Optional<Team> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Team entity) {

    }

    @Override
    public void update(Team entity) {

    }

    @Override
    public void delete(Team entity) {

    }
}
