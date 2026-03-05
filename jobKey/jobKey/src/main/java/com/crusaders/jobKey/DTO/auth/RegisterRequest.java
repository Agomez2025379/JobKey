package com.crusaders.jobKey.DTO.auth;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

    @NotBlank
    public String name;

    @Email
    @NotBlank
    public String email;

    @NotBlank
    public String password;

    @NotBlank
    public String tipoUsuario;
}