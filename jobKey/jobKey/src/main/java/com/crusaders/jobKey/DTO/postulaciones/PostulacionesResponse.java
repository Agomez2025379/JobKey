package com.crusaders.jobKey.DTO.postulaciones;

import com.crusaders.jobKey.enums.EEstado;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostulacionesResponse {

    private Integer idPostulacion;

    private Integer ofertaId;
    private String tituloOferta;

    private Integer candidatoId;
    private String nombreCandidato;

    private LocalDateTime fechaPostulacion;

    private EEstado estado;

    private String comentarios;

}