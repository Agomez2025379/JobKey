// AdminRequest.java
package com.crusaders.jobKey.dto.admins;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
}