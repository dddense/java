package ru.itis.javalab.services;

import ru.itis.javalab.repositories.old.CookiesRepository;

import java.util.List;

public class CookiesServiceImpl implements CookiesService {

    private CookiesRepository cookiesRepository;

    public CookiesServiceImpl(CookiesRepository cookiesRepository) {

        this.cookiesRepository = cookiesRepository;
    }

    @Override
    public List<String> getByValue(String value) {

        return cookiesRepository.findByValue(value);
    }

    @Override
    public void add(String value) {

        cookiesRepository.save(value);
    }
}
