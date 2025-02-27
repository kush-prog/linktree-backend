package com.linktr.backend.linktr_backend.controllers;

import com.linktr.backend.linktr_backend.dtos.LoginDto;
import com.linktr.backend.linktr_backend.dtos.LoginResponseDto;
import com.linktr.backend.linktr_backend.dtos.RegisterDto;
import com.linktr.backend.linktr_backend.dtos.UserResponse;
import com.linktr.backend.linktr_backend.models.User;
import com.linktr.backend.linktr_backend.service.AuthService;
import com.linktr.backend.linktr_backend.service.Implementation.JwtService;
import com.linktr.backend.linktr_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.linktr.backend.linktr_backend.mapper.UserMapper.toUserResponse;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterDto registerDto){
        var user = authService.registerUser(registerDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Boolean> confirmUserAccount(@RequestParam("token") String token){
        Boolean isSuccess = authService.verifyToken(token);
        return new ResponseEntity<>(isSuccess, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> findByUsername(@PathVariable String username){
        UserResponse user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginDto loginDto) {
        User authenticatedUser = authService.loginUser(loginDto);
        UserResponse response = toUserResponse(authenticatedUser);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setAccessToken(jwtToken);
        loginResponse.setUser(response);

        return ResponseEntity.ok(loginResponse);
    }
}

