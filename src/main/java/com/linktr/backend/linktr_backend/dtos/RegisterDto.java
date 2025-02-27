package com.linktr.backend.linktr_backend.dtos;

import lombok.Data;

@Data
public class RegisterDto {

    private String urlUsername;
    private String email;
    private String password;
}
