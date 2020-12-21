package ru.itis.javalab.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_BY_USERNAME = "select * from users where username = :username";
    //language=SQL
    private static final String SQL_ADD_USER = "insert into users (id, username, password, uuid) " +
            "values (default, :username, :password, :uuid)";

//    private SimpleJdbcTemplate template;
    private NamedParameterJdbcTemplate template;
//    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(NamedParameterJdbcTemplate template) {

        //this.dataSource = dataSource;
        this.template = template;
        //this.template = new SimpleJdbcTemplate(dataSource);
    }

    private final RowMapper<User> userRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .username(row.getString("username"))
            .password(row.getString("password"))
            .uuid(row.getString("uuid"))
            .build();

    @Override
    public void save(User entity) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", entity.getUsername());
        params.addValue("password", entity.getPassword());
        params.addValue("uuid", entity.getUuid());
        template.update(SQL_ADD_USER, params, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        entity.setId((long) keys.get("id"));
       // template.update(SQL_ADD_USER, entity.getUsername(), entity.getPassword(), entity.getUuid());
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
        return null;
    }

    @Override
    public List<User> findAll(int page, int size) {

        return null;
    }

    @Override
    public List<User> findAllByAge(int age) {
        return null;
    }

    @Override
    public List<User> findByUsername(String username) {

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        return template.query(SQL_FIND_BY_USERNAME, params, userRowMapper);
        //return template.query(SQL_FIND_BY_USERNAME, userRowMapper, username);
    }

}
