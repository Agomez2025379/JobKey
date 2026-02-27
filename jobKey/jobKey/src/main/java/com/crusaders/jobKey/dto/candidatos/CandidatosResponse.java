package com.crusaders.jobKey.dto.candidatos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CandidatosResponse {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String profesion;
    private String experiencia;
    private String educacion;
    private String habilidades;
    private String curriculumUrl;
    private Integer departamentoId;
    private String departamentoNombre;
    private LocalDateTime fechaRegistro;
    private LocalDateTime updatedAt;
}