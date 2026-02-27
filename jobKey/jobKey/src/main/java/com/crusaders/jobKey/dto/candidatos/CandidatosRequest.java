package com.crusaders.jobKey.dto.candidatos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CandidatosRequest {
    @NotBlank @Size(max = 100)
    private String nombre;
    @NotBlank @Size(max = 100)
    private String apellido;
    @NotBlank @Email @Size(max = 100)
    private String email;
    @NotBlank @Size(min = 8, max = 255)
    private String password;
    @Size(max = 20)
    private String telefono;
    @Size(max = 100)
    private String profesion;
    private String experiencia;
    private String educacion;
    private String habilidades;
    private String curriculumUrl;
    private Integer departamentoId;    // relación por id
}