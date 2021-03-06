package io.healthathome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

    public void sendEmail(SimpleMailMessage message) {
        emailSender.send(message);
    }
}
