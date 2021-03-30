package ru.itis.javalab.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
@Profile("master")
public class EmailUtilImpl implements EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ExecutorService executorService;


    @Override
    public void sendMail(String to, String from, String subject, String text) {

        executorService.submit(() -> javaMailSender.send(mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
        }));
    }
}
