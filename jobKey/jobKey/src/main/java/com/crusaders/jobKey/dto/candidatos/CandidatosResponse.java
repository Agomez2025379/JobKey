package com.crusaders.jobKey.DTO.candidatos;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CandidatosResponse {

    private Integer idCandidato;
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