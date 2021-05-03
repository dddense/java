package ru.itis.restfulapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.restfulapp.models.Course;

public interface CoursesRepository extends JpaRepository<Course, Long> {
}
