package ru.itis.restfulapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.restfulapp.dto.CourseDto;
import ru.itis.restfulapp.dto.TeacherDto;
import ru.itis.restfulapp.services.CoursesService;

import java.util.List;

@RestController
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {

        return ResponseEntity.ok(coursesService.getAllCourses());
    }

    @PostMapping("/course")
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto courseDto) {

        return ResponseEntity.ok(coursesService.addCourse(courseDto));
    }

    @PostMapping("/courses/{course-id}/teachers")
    public ResponseEntity<CourseDto> addTeacherIntoCourse(@RequestBody TeacherDto teacherDto,
                                                       @PathVariable("course-id") Long courseId) {

        return ResponseEntity.ok(coursesService.addTeacherIntoCourse(courseId, teacherDto));
    }
}
