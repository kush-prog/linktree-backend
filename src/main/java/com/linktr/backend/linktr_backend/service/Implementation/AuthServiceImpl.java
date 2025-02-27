package com.linktr.backend.linktr_backend.service.Implementation;

import com.linktr.backend.linktr_backend.dtos.LoginDto;
import com.linktr.backend.linktr_backend.dtos.RegisterDto;
import com.linktr.backend.linktr_backend.dtos.UserResponse;
import com.linktr.backend.linktr_backend.exceptions.ResourceNotFoundException;
import com.linktr.backend.linktr_backend.exceptions.UserAlreadyExistsException;
import com.linktr.backend.linktr_backend.models.EmailConfirmation;
import com.linktr.backend.linktr_backend.models.User;
import com.linktr.backend.linktr_backend.repository.EmailConfirmationRepository;
import com.linktr.backend.linktr_backend.repository.UserRepository;
import com.linktr.backend.linktr_backend.service.AuthService;
import com.linktr.backend.linktr_backend.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.linktr.backend.linktr_backend.mapper.UserMapper.toUserResponse;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final EmailConfirmationRepository emailConfirmationRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public UserResponse registerUser(RegisterDto register) {
        if (userRepository.findByUrlUsername(register.getUrlUsername()).isPresent()){
            throw new UserAlreadyExistsException("username already exists");
        }
        if (userRepository.findByEmail(register.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("email already in use");
        }
        User user = User.builder()
                .urlUsername(register.getUrlUsername())
                .email(register.getEmail())
                .isEnabled(false)
                .password(passwordEncoder.encode(register.getPassword()))
                .build();

        userRepository.save(user);
        UserResponse response = toUserResponse(user);

        EmailConfirmation emailConfirmation = EmailConfirmation.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .build();

        emailConfirmationRepository.save(emailConfirmation);

        emailService.sendSimpleMailMessage(user.getUrlUsername(), user.getEmail(), emailConfirmation.getToken());
        return response;
    }

    @Override
    public Boolean verifyToken(String token) {
        EmailConfirmation confirmation = emailConfirmationRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("token not found"));

        User user = userRepository.findByEmail(confirmation.getUser().getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        if (user != null){
            user.setEnabled(true);
            userRepository.save(user);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public User loginUser(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        if (!user.isEnabled()){
            throw new ResourceNotFoundException("Account is not activated");
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed");
        }
        return user;
    }
}