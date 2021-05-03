package ru.itis.restfulapp.services;

import ru.itis.restfulapp.dto.CourseDto;
import ru.itis.restfulapp.dto.TeacherDto;
import ru.itis.restfulapp.models.Course;

import java.util.List;
import java.util.Optional;

public interface CoursesService {

    CourseDto findById(Long id);

    List<CourseDto> getAllCourses();

    CourseDto addCourse(CourseDto courseDto);

    CourseDto addTeacherIntoCourse(Long courseId, TeacherDto teacherDto);
}
