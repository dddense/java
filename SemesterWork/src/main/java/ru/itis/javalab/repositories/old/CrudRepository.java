package ru.itis.javalab.repositories.old;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    List<T> findAll();
    Optional<T> findById(int id);

    void save(T entity);
    void update(T entity);
    void delete(T entity);
}
