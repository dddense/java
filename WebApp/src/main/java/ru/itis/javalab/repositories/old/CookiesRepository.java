package ru.itis.javalab.repositories.old;

import ru.itis.javalab.repositories.CrudRepository;

import java.util.List;

public interface CookiesRepository extends CrudRepository<String> {

    List<String> findByValue(String value);
}
