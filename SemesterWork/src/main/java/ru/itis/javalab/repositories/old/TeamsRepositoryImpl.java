package ru.itis.javalab.repositories.old;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.Team;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Profile("master")
@Repository
public class TeamsRepositoryImpl implements TeamsRepository {

    //language=SQL
    private static String SQL_FIND_ALL_BY_LEAGUE_ID = "select * from teams where league_id = :leaugueId";
    //language=SQL
    private static String SQL_FIND_BY_NAME = "select * from teams where name = :name";
    //language=SQL
    private static String SQL_FIND_ALL = "select * from teams";
    //language=SQL
    private static String SQL_FIND_BY_ID = "select * from teams where id = :id";

    private NamedParameterJdbcTemplate template;

    private RowMapper<Team> teamRowMapper = (row, rowNumber) ->

            Team.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
//                    .leagueId(row.getInt("league_id"))
                    .build();

    public TeamsRepositoryImpl(DataSource dataSource) {

        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Team> findAllByLeagueId(int id) {

        Map<String, Integer> params = new HashMap<>();
        params.put("leagueId", id);
        return template.query(SQL_FIND_ALL_BY_LEAGUE_ID, params, teamRowMapper);
    }

    @Override
    public Team findByName(String name) {

        Map<String, String> params = new HashMap<>();
        params.put("name", name);
//        return template.query(SQL_FIND_BY_NAME, params, teamRowMapper);
        return template.queryForObject(SQL_FIND_BY_NAME, params, teamRowMapper);
    }

    @Override
    public List findAll() {

        return template.query(SQL_FIND_ALL, teamRowMapper);
    }

    @Override
    public Optional<Team> findById(int id) {

        //TODO: make method return Team
        Map<String, Integer> params = new HashMap<>();
        params.put("id", id);
        return Optional.of(template.query(SQL_FIND_BY_ID, params, teamRowMapper).get(0));
    }

    @Override
    public void save(Team entity) {

    }

    @Override
    public void update(Team entity) {

    }

    @Override
    public void delete(Team entity) {

    }
}
