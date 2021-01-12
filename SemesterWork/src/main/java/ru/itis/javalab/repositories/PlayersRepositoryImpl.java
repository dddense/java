package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Player;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PlayersRepositoryImpl implements PlayersRepository {

    private NamedParameterJdbcTemplate template;

    //language=SQL
    private static String SQL_FIND_ALL_BY_TEAM_ID = "select * from players where team_id = :team_id";
    //language=SQL
    private static String SQL_FIND_ALL_BY_TEAM_NAME = "select * from players where id = (select (id) from teams where name = :name)";
    //language=SQL
    private static String SQL_FIND_ALL_BY_ROLE = "select * from players where pos = :pos";
    //language=SQL
    private static String SQL_FIND_ALL_BY_AGE = "select * from players where age = :age";
    //language=SQL
    private static String SQL_FIND_ALL_BY_NAME = "select * from players where name = :name";
    //language=SQL
    private static String SQL_FIND_ALL = "select * from players";
    //language=SQL
    private static String SQL_FIND_BY_ID = "select 1 from players where id = :id";
    //language=SQL
    private static String SQL_FIND_BY_NAME = "select 1 from players where name = :name";

    public PlayersRepositoryImpl(DataSource dataSource) {

        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<Player> rowMapper = (row, rowNumber) ->
            Player.builder()
                    .age(row.getInt("age"))
                    .id(row.getInt("id"))
                    .name(row.getString("name"))
                    .pos(row.getInt("pos"))
                    .teamId(row.getInt("team_id"))
                    .build();

    @Override
    public List<Player> findAllByTeamId(int id) {

        Map<String, Object> params = new HashMap<>();
        params.put("team_id", id);
        return template.query(SQL_FIND_ALL_BY_TEAM_ID, params, rowMapper);
    }

    @Override
    public List<Player> findAllByRole(int id) {

        Map<String, Object> params = new HashMap<>();
        params.put("pos", id);
        return template.query(SQL_FIND_ALL_BY_ROLE, params, rowMapper);
    }

    @Override
    public List<Player> findAllByAge(int age) {

        Map<String, Object> params = new HashMap<>();
        params.put("age", age);
        return template.query(SQL_FIND_ALL_BY_AGE, params, rowMapper);
    }

    @Override
    public List<Player> findAllByName(String name) {

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return template.query(SQL_FIND_ALL_BY_TEAM_NAME, params, rowMapper);
    }

    @Override
    public Optional<Player> findByName(String name) {

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);

        return Optional.ofNullable(template.queryForObject(SQL_FIND_BY_NAME, params, rowMapper));
    }

    @Override
    public List<Player> findAllByTeamName(String name) {

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);

        return template.query(SQL_FIND_ALL_BY_TEAM_NAME, params, rowMapper);
    }

    @Override
    public List<Player> findAll() {

        return template.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public Optional<Player> findById(int id) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return Optional.of(template.query(SQL_FIND_BY_ID, params, rowMapper).get(0));
    }
}
