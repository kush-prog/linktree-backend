package com.linktr.backend.linktr_backend.dtos;

import lombok.Data;

@Data
public class ChangePassword {
    private String email;
    private String oldPassword;
    private String newPassword;
}
