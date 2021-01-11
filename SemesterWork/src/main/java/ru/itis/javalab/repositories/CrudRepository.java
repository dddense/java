package ru.itis.javalab.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    List<T> findAll();
    Optional<T> findById(int id);
}
