package ru.itis.restfulapp.services;

import ru.itis.restfulapp.dto.EmailPasswordDto;
import ru.itis.restfulapp.dto.TokenDto;

import java.util.Map;

public interface LoginService {

    TokenDto login(EmailPasswordDto emailPassword);
}
