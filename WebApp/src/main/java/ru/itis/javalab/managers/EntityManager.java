package ru.itis.javalab.managers;

import ru.itis.javalab.repositories.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EntityManager {

    private DataSource dataSource;
    private Map<String, String> types;
    private SimpleJdbcTemplate template;

    public EntityManager(DataSource dataSource) {

        this.dataSource = dataSource;
        types = new HashMap<>();
        types.put("Integer", "int");
        types.put("String", "varchar(255)");
        types.put("Long", "bigint");
        types.put("Boolean", "boolean");
        template = new SimpleJdbcTemplate(dataSource);
    }

    public EntityManager() {

        types = new HashMap<>();
        types.put("Integer", "int");
        types.put("String", "varchar(255)");
        types.put("Long", "bigint");
        types.put("Boolean", "boolean");
    }

    // createTable("account", User.class);
    public <T> void createTable(String tableName, Class<T> entityClass) {

        // сгенерировать CREATE TABLE на основе класса
        // create table account ( id integer, firstName varchar(255), ...))
        StringBuilder sql = new StringBuilder();
        sql.append("create table ").append(tableName).append("(\n");
        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            sql.append(fields[i].getName()).append(" ").append(types.get(fields[i].getType().getSimpleName()));
            if (i < fields.length - 1) {
                sql.append(",\n");
            }
        }
        sql.append("\n);");
        template.update(sql.toString());
    }

    public void save(String tableName, Object entity) {

        Class<?> classOfEntity = entity.getClass();
        // сканируем его поля
        // сканируем значения этих полей
        // генерируем insert into
        StringBuilder sql = new StringBuilder();
        sql.append("insert into").append(" ").append(tableName).append(" ");
        Field[] fields = classOfEntity.getDeclaredFields();
        sql.append("values").append(" ").append("(");
        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].isAccessible()) {
                fields[i].setAccessible(true);
            }
            String type = fields[i].getType().getSimpleName();
            if (type.equals("String") || type.equals("Boolean")) {
                try {
                    sql.append("'").append(fields[i].get(entity)).append("'");
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            } else {
                try {
                    sql.append(fields[i].get(entity));
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
            if (i < fields.length - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
        template.update(sql.toString());
    }

    // User user = entityManager.findById("account", User.class, Long.class, 10L);
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {

        // сгенерировать select
        T instance;
        StringBuilder sql = new StringBuilder("select *").append(" from ")
                .append(tableName).append(" where id = ")
                .append(idValue.toString()).append(" limit 1");
        Connection connection = null;
        ResultSet resultSet;
        Field[] fields = resultType.getDeclaredFields();

        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(String.valueOf(sql));
            resultSet = statement.executeQuery();
            instance = resultType.getConstructor().newInstance();
            for (Field field : fields) {
                field.set(instance, resultSet.getObject(field.getName()));
            }
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException
                | IllegalAccessException | SQLException e) {
            throw new IllegalStateException(e);
        }

        return instance;
    }
}
