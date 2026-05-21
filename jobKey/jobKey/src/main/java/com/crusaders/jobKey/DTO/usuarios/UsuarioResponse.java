package com.crusaders.jobKey.dto.usuarios;

import com.crusaders.jobKey.enums.EUsuarioRol;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsuarioResponse {
    private Integer idUsuario;
    private String email;
    private EUsuarioRol rol;
    private LocalDateTime ultimoAcceso;
    private LocalDateTime fechaRegistro;
}