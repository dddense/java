package ru.itis.semestralwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class SemestralWorkApplication {

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public ExecutorService executorService() {

        return Executors.newFixedThreadPool(10);
    }

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(SemestralWorkApplication.class, args);
    }

}
