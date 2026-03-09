package com.crusaders.jobKey.DTO.auth;

import com.crusaders.jobKey.enums.EUsuarioRol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private String message;
    private Integer userId;
    private String email;
    private String nombre;
    private Integer entidadId;
    private EUsuarioRol rol;

}