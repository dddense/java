package ru.itis.javalab.services;

import ru.itis.javalab.models.Player;
import ru.itis.javalab.repositories.PlayersRepository;

import java.util.List;

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
}
