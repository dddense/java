package ru.itis.javalab.repositories;

import java.util.List;

public interface CookiesRepository extends CrudRepository<String> {

    List<String> findByValue(String value);
}
