package ru.itis.javalab.services;

import ru.itis.javalab.models.Student;

import java.util.List;

public interface StudentsService {

    List<Student> getStudentsByAge(int age);
    List<Student> getAll();
}
