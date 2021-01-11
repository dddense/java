package ru.itis.javalab.repositories;

import ru.itis.javalab.models.League;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LeaguesRepositoryImpl implements LeaguesRepository {

    private NamedParameterJdbcTemplate template;

    //language=SQL
    private static String SQL_FIND_BY_NAME = "select * from leagues where name = :name";
    //language=SQL
    private static String SQL_FIND_ALL = "select * from leagues";
    //language=SQL
    private static String SQL_FIND_BY_ID = "select * from leagues where id = :id";

    public LeaguesRepositoryImpl(NamedParameterJdbcTemplate template) {

        this.template = template;
    }

    private final RowMapper<League> rowMapper = (row, rowNumber) ->
            League.builder()
            .id(row.getInt("id"))
            .name(row.getString("name"))
            .build();

    @Override
    public Optional<League> findByName(String name) {

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return Optional.of(template.query(SQL_FIND_BY_NAME, params, rowMapper).get(0));
    }

    @Override
    public List<League> findAll() {

        return template.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public Optional<League> findById(int id) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return Optional.of(template.query(SQL_FIND_BY_ID, params, rowMapper).get(0));
    }
}
