package ru.itis.restfulapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.restfulapp.models.Course;
import ru.itis.restfulapp.models.Teacher;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {

    private String title;
    private List<String> teachers;

    public static CourseDto from(Course course) {

        return CourseDto.builder()
                .teachers(course.getTeachers().stream().map(Teacher::getLastName).collect(Collectors.toList()))
                .title(course.getTitle())
                .build();
    }

    public static List<CourseDto> from(List<Course> courses) {

        return courses.stream().map(CourseDto::from).collect(Collectors.toList());
    }
}
