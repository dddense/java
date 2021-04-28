package ru.itis.restfulapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.restfulapp.dto.TeacherDto;
import ru.itis.restfulapp.models.Teacher;
import ru.itis.restfulapp.repositories.TeachersRepository;

import java.util.List;

import static ru.itis.restfulapp.dto.TeacherDto.from;

@Service
public class TeachersServiceImpl implements TeachersService {

    @Autowired
    private TeachersRepository teachersRepository;

    @Override
    public List<TeacherDto> getAllTeachers() {

        return from(teachersRepository.findAllByIsDeletedIsNull());
    }

    @Override
    public TeacherDto addTeacher(TeacherDto teacher) {

        Teacher newTeacher = Teacher.builder()
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .build();

        teachersRepository.save(newTeacher);

        return from(newTeacher);
    }

    @Override
    public TeacherDto updateTeacher(Long id, TeacherDto teacherDto) {

        Teacher teacherForUpdate;
        try {
            teacherForUpdate = teachersRepository.findById(id).orElseThrow(IllegalStateException::new);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        teacherForUpdate.setFirstName(teacherDto.getFirstName());
        teacherForUpdate.setLastName(teacherDto.getLastName());

        teachersRepository.save(teacherForUpdate);

        return from(teacherForUpdate);
    }

    @Override
    public void deleteTeacher(Long id) {

        Teacher teacherToDelete;
        try {
            teacherToDelete = teachersRepository.findById(id).orElseThrow(IllegalStateException::new);
            teacherToDelete.setIsDeleted(true);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        teacherToDelete.setIsDeleted(true);

        teachersRepository.save(teacherToDelete);
    }
}
