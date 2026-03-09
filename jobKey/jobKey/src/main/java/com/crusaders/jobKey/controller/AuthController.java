package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.DTO.auth.LoginRequest;
import com.crusaders.jobKey.DTO.auth.LoginResponse;
import com.crusaders.jobKey.DTO.auth.RegisterRequest;
import com.crusaders.jobKey.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public LoginResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}