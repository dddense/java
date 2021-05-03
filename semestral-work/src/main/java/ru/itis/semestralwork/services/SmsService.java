package ru.itis.semestralwork.services;

public interface SmsService {

    void sendMessage(String phone, String text);
}
