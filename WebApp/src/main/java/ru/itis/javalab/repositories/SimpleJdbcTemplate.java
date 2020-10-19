package ru.itis.javalab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcTemplate {

    private Connection connection;

    public SimpleJdbcTemplate(Connection connection) {

        this.connection = connection;
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object ... args) {

        try {
            ResultSet resultSet = null;
            PreparedStatement statement = connection.prepareStatement(sql);

            List<T> result = new ArrayList<>();
            int pos = 1;

            for (Object arg : args) {
                statement.setObject(pos, arg);
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }

            return result;
        } catch (SQLException throwable) {
            throw new IllegalStateException(throwable);
        }
    }
}
