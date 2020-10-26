package ru.itis.javalab.repositories;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcTemplate {

    private Connection connection;

    public SimpleJdbcTemplate(DataSource dataSource) {

        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) {

        try {
            ResultSet resultSet;
            PreparedStatement statement = connection.prepareStatement(sql);

            List<T> result = new ArrayList<>();
            int pos = 1;

            for (Object arg : args) {
                statement.setObject(pos, arg);
                pos++;
            }

            resultSet = statement.executeQuery();
            System.out.println(resultSet.getStatement());

            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }

            return result;
        } catch (SQLException throwable) {
            throw new IllegalStateException(throwable);
        }
    }

    public <T> void update(String sql, Object... args) {

        System.out.println(2);
        try {
            System.out.println(1);
            PreparedStatement statement = connection.prepareStatement(sql);

            int pos = 1;

            for (Object arg : args) {
                statement.setObject(pos, arg);
                pos++;
            }

            System.out.println(statement);

            statement.executeUpdate();

        } catch (SQLException throwable) {
            throw new IllegalStateException(throwable);
        }
    }
}
