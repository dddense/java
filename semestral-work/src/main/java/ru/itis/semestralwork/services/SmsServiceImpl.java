package ru.itis.semestralwork.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Value("${sms.url}")
    private String smsUrl;

    @Value("${sms.api_id}")
    private String apiId;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void sendMessage(String phone, String text) {

        String url = smsUrl + "?api_id=" + apiId + "&to=" + phone + "&msg=" + text + "&json=1";
        log.info(url);
        String result = restTemplate.getForObject(url, String.class);
        log.info(result);
    }
}
