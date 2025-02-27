package com.linktr.backend.linktr_backend.service;

import com.linktr.backend.linktr_backend.dtos.LoginDto;
import com.linktr.backend.linktr_backend.dtos.RegisterDto;
import com.linktr.backend.linktr_backend.dtos.UserResponse;
import com.linktr.backend.linktr_backend.models.User;

public interface AuthService {

    UserResponse registerUser(RegisterDto registerDto);

    Boolean verifyToken(String token);

    User loginUser(LoginDto loginDto);

}
