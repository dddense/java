package ru.itis.javalab.services;

import java.util.List;

public interface CookiesService {

    List<String> getByValue(String value);
    void add(String value);
}
