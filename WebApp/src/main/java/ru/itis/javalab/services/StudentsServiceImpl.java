package ru.itis.javalab.services;

import ru.itis.javalab.models.Student;
import ru.itis.javalab.repositories.StudentsRepository;

import java.util.List;

public class StudentsServiceImpl implements StudentsService {

    private StudentsRepository studentsRepository;

    public StudentsServiceImpl(StudentsRepository studentsRepository) {

        this.studentsRepository = studentsRepository;
    }

    @Override
    public List<Student> getStudentsByAge(int age) {

        return studentsRepository.findAllByAge(age);
    }

    @Override
    public List<Student> getAll() {

        return studentsRepository.findAll();
    }
}
