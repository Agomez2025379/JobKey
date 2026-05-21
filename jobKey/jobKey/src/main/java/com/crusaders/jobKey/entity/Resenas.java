package com.crusaders.jobKey.entity;

import com.crusaders.jobKey.enums.ResenaTipo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonPropertyOrder({
        "idResena",
        "tipo",
        "empresaId",
        "candidatoId",
        "ofertaId",
        "puntuacion",
        "comentario",
        "fecha"
})
@Entity
@Table(name = "resenas")
public class Resenas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Integer idResena;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, columnDefinition = "enum('empresa_a_candidato','candidato_a_empresa')")
    private ResenaTipo tipo;

    //id de la empresa
    @Column(name = "empresa_id")
    private Integer empresaId;

    //id del cadidato
    @Column(name = "candidato_id")
    private Integer candidatoId;

    //id de la oferta
    @Column(name = "oferta_id")
    private Integer ofertaId;

    //la puntuacion
    @Column(name = "puntuacion")
    private Integer puntuacion;

    //el comentario de la reseña
    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    //fecha automatica
    @Column(name = "fecha", insertable = false, updatable = false)
    private LocalDateTime fecha;
}
