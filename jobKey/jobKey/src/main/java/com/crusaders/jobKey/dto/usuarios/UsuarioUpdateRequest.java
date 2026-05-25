package com.crusaders.jobKey.dto.usuarios;

import com.crusaders.jobKey.enums.EUsuarioRol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateRequest {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Ingresa un email válido")
    @Size(max = 100)
    private String email;

    @NotNull(message = "El rol es obligatorio")
    private EUsuarioRol rol;
}