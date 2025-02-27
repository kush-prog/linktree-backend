package com.linktr.backend.linktr_backend.service.Implementation;

import com.linktr.backend.linktr_backend.Utils.EmailUtils;
import com.linktr.backend.linktr_backend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("http://localhost:8080")
    private String host;
    @Value("${EMAIL_USERNAME}")
    private String fromEmail;
    private final JavaMailSender emailSender;

    @Override
    @Async
    public void sendSimpleMailMessage(String name, String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("New User Account Verification");
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText(EmailUtils.getEmailMessage(name, host, token));
            emailSender.send(message);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}

