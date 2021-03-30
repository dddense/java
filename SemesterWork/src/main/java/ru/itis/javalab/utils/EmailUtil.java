package ru.itis.javalab.utils;

import org.springframework.stereotype.Component;

@Component
public interface EmailUtil {

    void sendMail(String to, String from, String subject, String text);
}
