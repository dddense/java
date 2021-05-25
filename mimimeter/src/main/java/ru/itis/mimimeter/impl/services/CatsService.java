package ru.itis.mimimeter.impl.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.mimimeter.impl.dto.CatDto;
import ru.itis.mimimeter.impl.models.CatForm;
import ru.itis.mimimeter.impl.models.User;

import java.util.List;
import java.util.Map;

public interface CatsService {

    CatDto addCat(CatForm catForm, User user, MultipartFile file);

    List<CatDto> getAll();

    CatDto getById(Long id);

    List<CatDto> getTopTen();

    Map<CatDto, CatDto> getAllPairs();

    CatDto vote(Long id, User voter);
}
