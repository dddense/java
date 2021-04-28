package ru.itis.restfulapp.services;

import org.springframework.stereotype.Service;
import ru.itis.restfulapp.dto.TeacherDto;
import ru.itis.restfulapp.models.Teacher;

import java.util.List;

public interface TeachersService {

    List<TeacherDto> getAllTeachers();

    TeacherDto addTeacher(TeacherDto teacher);

    TeacherDto updateTeacher(Long id, TeacherDto teacherDto);

    void deleteTeacher(Long id);
}
