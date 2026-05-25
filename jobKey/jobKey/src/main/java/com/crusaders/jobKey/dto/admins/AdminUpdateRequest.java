package com.crusaders.jobKey.dto.admins;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUpdateRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;
}