package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from student";
    //language=SQL
    private static final String SQL_FIND_ALL_BY_AGE = "select * from student where age = ?";
    //language=SQL
    private static final String SQL_FIND_BY_USERNAME = "select * from users where username = ?";
    //language=SQL
    private static final String SQL_ADD_USER = "insert into users (username, password, uuid) values (?, ?, ?)";

    private SimpleJdbcTemplate template;
    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {

        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    private final RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    private final RowMapper<User> authRowMapper = row -> User.builder()
            .username(row.getString("username"))
            .password(row.getString("password"))
            .uuid(row.getString("uuid"))
            .build();

    @Override
    public void save(User entity) {

        template.update(SQL_ADD_USER, entity.getUsername(), entity.getPassword(), entity.getUuid());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {

        return template.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public List<User> findAllByAge(int age) {

        return template.query(SQL_FIND_ALL_BY_AGE, userRowMapper, age);
    }

    @Override
    public List<User> findByUsername(String username) {

        return template.query(SQL_FIND_BY_USERNAME, authRowMapper, username);
    }

}
