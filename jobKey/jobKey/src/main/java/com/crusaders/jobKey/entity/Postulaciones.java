package com.crusaders.jobKey.entity;

import com.crusaders.jobKey.enums.EEstado;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table( name = "postulaciones")
public class Postulaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postulacion")
    private Integer idPostulacion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oferta_id")
    private OfertasTrabajo ofertaTrabajo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidato_id")
    private Candidatos candidato;

    @Column(name = "fecha_postulacion")
    private LocalDateTime fechaPostulacion;

    @Column(name = "estado")
    private EEstado estado;

    @Column(name = "comentarios")
    private String comentarios;





}
