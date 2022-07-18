package com.barisyenigun.ToDo.controller;

import com.barisyenigun.ToDo.request.LoginRequest;
import com.barisyenigun.ToDo.request.RegisterRequest;
import com.barisyenigun.ToDo.response.AuthResponse;
import com.barisyenigun.ToDo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@Slf4j
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest body){
        authService.register(body);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest body){
        return authService.login(body);
    }
}
