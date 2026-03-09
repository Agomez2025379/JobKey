package com.crusaders.jobKey.DTO.candidatos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidatosRequest {

    private Integer usuarioId;

    private String nombre;
    private String apellido;
    private String telefono;
    private String profesion;
    private String experiencia;
    private String educacion;
    private String habilidades;

    private String curriculumUrl;

    private Integer departamentoId;

}