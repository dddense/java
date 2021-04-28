package ru.itis.restfulapp.services;

import ru.itis.restfulapp.dto.CourseDto;
import ru.itis.restfulapp.dto.TeacherDto;

import java.util.List;

public interface CoursesService {

    List<CourseDto> getAllCourses();

    CourseDto addCourse(CourseDto courseDto);

    CourseDto addTeacherIntoCourse(Long courseId, TeacherDto teacherDto);
}
