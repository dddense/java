package ru.itis.semestralwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.semestralwork.dto.PostDto;
import ru.itis.semestralwork.models.Post;
import ru.itis.semestralwork.repositories.PostsRepository;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;


}
