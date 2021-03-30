package ru.itis.javalab.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class EmailUtilMockImpl implements EmailUtil {

    @Autowired
    private MailsGenerator mailsGenerator;

    @Value("${server.url}")
    private String serverUrl;

    @Override
    public void sendMail(String to, String from, String subject, String text) {


    }
}
