package ru.itis.restfulapp.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.restfulapp.dto.TeacherDto;
import ru.itis.restfulapp.services.TeachersService;

import java.util.List;

@RestController
public class TeachersController {

    @Autowired
    private TeachersService teachersService;

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDto>> getTeacher(@RequestHeader("access-token") String token) {

        return ResponseEntity.ok(teachersService.getAllTeachers());
    }

    @ApiOperation(value = "Добавление педагога")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено", response = TeacherDto.class)})
    @PostMapping("/teachers")
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody TeacherDto teacher,
                                                 @RequestHeader("access-token") String token) {

        return ResponseEntity.ok(teachersService.addTeacher(teacher));
    }

    @PutMapping("/teachers/{teacher-id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable("teacher-id") Long id,
                                                    @RequestBody TeacherDto teacherDto,
                                                    @RequestHeader("access-token") String token) {

        return ResponseEntity.ok(teachersService.updateTeacher(id, teacherDto));
    }

    @DeleteMapping("/teachers/{teacher-id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("teacher-id") Long id,
                                           @RequestHeader("access-token") String token) {

        teachersService.deleteTeacher(id);

        return ResponseEntity.ok().build();
    }
}
