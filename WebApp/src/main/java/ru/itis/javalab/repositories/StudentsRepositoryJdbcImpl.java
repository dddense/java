package ru.itis.javalab.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.javalab.models.Student;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentsRepositoryJdbcImpl implements StudentsRepository {

    private NamedParameterJdbcTemplate template;
    //private SimpleJdbcTemplate template;

    public StudentsRepositoryJdbcImpl(NamedParameterJdbcTemplate template) {

        this.template = template;
        //this.template = new SimpleJdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from student";
    //language=SQL
    private static final String SQL_FIND_ALL_BY_AGE = "select * from student where age = :age";

    private final RowMapper<Student> studentsRowMapper = (row, rowNumber) -> Student.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    @Override
    public List<Student> findAllByAge(int age) {

        return template.query(SQL_FIND_ALL_BY_AGE, Collections.singletonMap("age", age), studentsRowMapper);
//        return template.query(SQL_FIND_ALL_BY_AGE, studentsRowMapper, age);
    }

    @Override
    public void save(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {

        return template.query(SQL_FIND_ALL, studentsRowMapper);
    }

    @Override
    public List findAll(int page, int size) {
        return null;
    }
}
