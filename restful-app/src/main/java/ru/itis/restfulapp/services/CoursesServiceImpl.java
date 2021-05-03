package ru.itis.restfulapp.services;

import org.hibernate.service.UnknownServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.restfulapp.dto.CourseDto;
import ru.itis.restfulapp.dto.TeacherDto;
import ru.itis.restfulapp.models.Course;
import ru.itis.restfulapp.models.Teacher;
import ru.itis.restfulapp.repositories.CoursesRepository;
import ru.itis.restfulapp.repositories.TeachersRepository;

import java.util.List;
import java.util.Optional;

import static ru.itis.restfulapp.dto.CourseDto.from;

@Service
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private TeachersRepository teachersRepository;


    @Override
    public CourseDto findById(Long id) {

        return from(coursesRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("")));
    }

    @Override
    public List<CourseDto> getAllCourses() {

        return from(coursesRepository.findAll());
    }

    public CourseDto addCourse(CourseDto courseDto) {

        return from(coursesRepository.save(Course.builder()
                .title(courseDto.getTitle())
                .build()));
    }

    @Override
    public CourseDto addTeacherIntoCourse(Long courseId, TeacherDto teacherDto) {

        Course course = coursesRepository.findById(courseId).orElseThrow(IllegalArgumentException::new);
        Teacher teacher = teachersRepository.findById(teacherDto.getId()).orElseThrow(IllegalArgumentException::new);
        course.getTeachers().add(teacher);
        coursesRepository.save(course);

        return from(course);
    }
}
