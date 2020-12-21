package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Student;
import ru.itis.javalab.repositories.CrudRepository;

import java.util.List;

public interface StudentsRepository extends CrudRepository {

    List<Student> findAllByAge(int age);
}
