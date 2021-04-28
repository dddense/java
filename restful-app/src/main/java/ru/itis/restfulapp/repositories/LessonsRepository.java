package ru.itis.restfulapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.restfulapp.models.Lesson;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {
}
