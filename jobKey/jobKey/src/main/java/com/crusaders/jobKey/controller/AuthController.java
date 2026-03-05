package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.DTO.auth.LoginRequest;
import com.crusaders.jobKey.DTO.auth.LoginResponse;
import com.crusaders.jobKey.DTO.auth.RegisterRequest;
import com.crusaders.jobKey.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/{tipoUsuario}")
    public String register(
            @PathVariable String tipoUsuario,
            @Valid @RequestBody RegisterRequest req) {

        authService.register(req, tipoUsuario);
        return "Usuario registrado correctamente.";
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest req) {
        return authService.login(req);
    }
}