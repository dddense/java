package ru.itis.javalab.services;

import ru.itis.javalab.models.Player;
import ru.itis.javalab.repositories.PlayersRepository;

import java.util.List;
import java.util.Optional;

public class PlayerServiceImpl implements PlayerService {

    private PlayersRepository playersRepository;

    public PlayerServiceImpl(PlayersRepository playersRepository) {

        this.playersRepository = playersRepository;
    }

    @Override
    public List<Player> getAllByTeamId(int id) {

        return playersRepository.findAllByTeamId(id);
    }

    @Override
    public List<Player> getAllByRoleId(int id) {

        return playersRepository.findAllByRole(id);
    }

    @Override
    public List<Player> getAllByAge(int age) {

        return playersRepository.findAllByAge(age);
    }

    @Override
    public List<Player> getAllName(String name) {

        return playersRepository.findAllByName(name);
    }

    @Override
    public List<Player> getAllByTeamName(String name) {

        return playersRepository.findAllByTeamName(name);
    }

    @Override
    public Optional<Player> getByName(String name) {

        return playersRepository.findByName(name);
    }

    @Override
    public Optional<Player> getById(Integer id) {

        return playersRepository.findById(id);
    }
}
