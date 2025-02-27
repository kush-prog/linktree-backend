package com.linktr.backend.linktr_backend.dtos;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String accessToken;
    private UserResponse user;
}
