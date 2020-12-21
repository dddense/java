package ru.itis.javalab.repositories;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CookiesRepositoryJdbcImpl implements CookiesRepository {

    //language=SQL
    private static final String SQL_FIND_BY_VALUE = "select * from cookies where value = ?";
    //language=SQL
    private static final String SQL_ADD = "insert into cookies (value) values (?)";

    private DataSource dataSource;
    private SimpleJdbcTemplate template;

    public CookiesRepositoryJdbcImpl(DataSource dataSource) {

        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    private RowMapper<String> stringRowMapper = row -> row.getString("value");

    @Override
    public List<String> findByValue(String value) {

        return template.query(SQL_FIND_BY_VALUE, stringRowMapper, value);
    }

    @Override
    public void save(String entity) {

        template.update(SQL_ADD, entity);
    }

    @Override
    public void update(String entity) {

    }

    @Override
    public Optional<String> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<String> findAll() {
        return null;
    }
}
