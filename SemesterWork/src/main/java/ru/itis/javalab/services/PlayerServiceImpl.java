package ru.itis.javalab.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.models.Player;
import ru.itis.javalab.repositories.PlayersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayersRepository playersRepository;

    public PlayerServiceImpl(PlayersRepository playersRepository) {

        this.playersRepository = playersRepository;
    }

    @Override
    public List<Player> getAll() {

        return playersRepository.findAll();
    }

    @Override
    public List<Player> getAllByTeamId(int id) {

//        return playersRepository.findAllByTeamId(id);
        return null;
    }

    @Override
    public List<Player> getAllByRoleId(int id) {

//        return playersRepository.findAllByRole(id);
        return null;
    }

    @Override
    public List<Player> getAllByAge(int age) {

//        return playersRepository.findAllByAge(age);
        return null;
    }

    @Override
    public List<Player> getAllName(String name) {

//        return playersRepository.findAllByName(name);
        return null;
    }

    @Override
    public List<Player> getAllByTeamName(String name) {

//        return playersRepository.findAllByTeamName(name);
        return null;
    }

    @Override
    public Optional<Player> getByName(String name) {

//        return playersRepository.findByName(name);
        return null;
    }

    @Override
    public Optional<Player> getById(Long id) {

        return playersRepository.findById(id);
    }
}
