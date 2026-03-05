package com.crusaders.jobKey.DTO.postulaciones;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostulacionesRequest {

    @NotNull(message = "El id de la oferta es obligatorio")
    private Integer ofertaId;

    @NotNull(message = "El id del candidato es obligatorio")
    private Integer candidatoId;

    @Size(max = 2000, message = "Los comentarios no pueden superar los 2000 caracteres")
    private String comentarios;

}