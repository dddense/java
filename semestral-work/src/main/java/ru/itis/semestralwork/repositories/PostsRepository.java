package ru.itis.semestralwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.semestralwork.models.Post;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {

    List<Post> findByAccountId(Long id);
}
