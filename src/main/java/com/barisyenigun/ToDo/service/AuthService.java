package com.barisyenigun.ToDo.service;

import com.barisyenigun.ToDo.entity.User;
import com.barisyenigun.ToDo.exception.PasswordsNotMatchedException;
import com.barisyenigun.ToDo.exception.UserNotFoundException;
import com.barisyenigun.ToDo.repository.UserRepository;
import com.barisyenigun.ToDo.request.LoginRequest;
import com.barisyenigun.ToDo.request.RegisterRequest;
import com.barisyenigun.ToDo.response.AuthResponse;
import com.barisyenigun.ToDo.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public void register(RegisterRequest body){
        User existingUser = userRepository.findByUsername(body.getUsername());
        if (existingUser != null){
            throw new IllegalStateException("Username already taken!");
        }

        User user = new User();
        user.setUsername(body.getUsername());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        userRepository.save(user);
    }


    public AuthResponse login(LoginRequest body){
        User user = userRepository.findByUsername(body.getUsername());
        if (user == null){
            throw new UserNotFoundException();
        }
        if (!passwordEncoder.matches(body.getPassword(), user.getPassword())){
            throw new PasswordsNotMatchedException();
        }

        return AuthResponse.builder()
                .id(user.getId())
                .token(jwtService.createToken(user.getId().toString()))
                .build();
    }
}
