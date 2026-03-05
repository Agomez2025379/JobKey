package com.crusaders.jobKey.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LoginResponse {
    private Integer id;
    private String nombre;
    private String email;
    private String tipoUsuario;
    private String mensaje;
}