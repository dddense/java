package ru.itis.mimimeter.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.mimimeter.impl.dto.CatDto;
import ru.itis.mimimeter.impl.models.Cat;
import ru.itis.mimimeter.impl.models.CatForm;
import ru.itis.mimimeter.impl.models.User;
import ru.itis.mimimeter.impl.repositories.CatsRepository;
import ru.itis.mimimeter.impl.repositories.UsersRepository;

import java.io.*;
import java.util.*;

import static ru.itis.mimimeter.impl.dto.CatDto.from;

@Service
public class CatsServiceImpl implements CatsService {

    private CatsRepository catsRepository;

    private UsersRepository usersRepository;

    @Autowired
    public CatsServiceImpl(CatsRepository catsRepository,
                           UsersRepository usersRepository) {

        this.catsRepository = catsRepository;
        this.usersRepository = usersRepository;
    }

    @Value("${storage.path}")
    private String storagePath;

    @Override
    public CatDto addCat(CatForm catForm, User user, MultipartFile file) {

        String fileName = UUID.randomUUID().toString() + ".jpg";
        File imageFile = new File(storagePath + fileName);
        try {
            OutputStream os = new FileOutputStream(imageFile);
            os.write(file.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        Cat cat = Cat.builder()
                .name(catForm.getName())
                .owner(user)
                .imagePath(storagePath + fileName)
                .voters(new HashSet<>())
                .build();

        return from(catsRepository.save(cat));
    }

    @Override
    public List<CatDto> getAll() {

        return from(catsRepository.findAll());
    }

    @Override
    public CatDto getById(Long id) {

        return from(catsRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Cat not found")));
    }

    @Override
    public List<CatDto> getTopTen() {

        return from(catsRepository.findTop10ByVotes());
    }

    @Override
    public Map<CatDto, CatDto> getAllPairs() {

        List<CatDto> cats = getAll();
        Collections.shuffle(cats);
        if (cats.size() % 2 != 0) {
            cats.remove(cats.size() - 1);
        }
        Map<CatDto, CatDto> pairs = new HashMap<>();
        CatDto previous = null;
        int counter = 1;

        for (CatDto cat : cats) {
            if (counter % 2 == 0) {
                pairs.put(previous, cat);
            } else {
                pairs.put(cat, null);
            }
            previous = cat;
            counter++;
        }

        return pairs;
    }

    @Override
    public CatDto vote(Long id, User voter) {

        Cat cat = catsRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Cat not found"));
        cat.getVoters().add(voter);
        voter.getVotedCats().add(cat);
        catsRepository.save(cat);
        usersRepository.save(voter);

        return from(cat);
    }
}
