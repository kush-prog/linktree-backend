package com.linktr.backend.linktr_backend.service;

public interface EmailService {

    void sendSimpleMailMessage(String name, String to, String token);
}

