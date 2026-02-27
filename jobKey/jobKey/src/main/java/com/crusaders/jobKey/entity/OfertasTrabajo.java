package com.crusaders.jobKey.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonPropertyOrder({
        "idOfertaTrabajo",
        "empresaID",
        "titulo",
        "descripcion",
        "requisitos",
        "salario",
        "modalidad",
        "tipoJornada",
        "nivelRequerido",
        "departamentoID",
        "fechaPublicacion",
        "fechaCierre",
        "activa"
})
@Entity
@Table(name = "ofertas_trabajo")
public class OfertasTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oferta")
    private Integer idOfertaTrabajo;

    @NotNull(message = "la empresa no puede ser nula")
    @Column(name = "empresa_id", nullable = false)
    private Integer empresaID;

    @NotNull(message = "el titulo no puede ser nulo")
    @NotEmpty(message = "el titulo no puede quedar vacio")
    @NotBlank(message = "el titulo no puede quedar vacio")
    @Size(min = 2, max = 50, message = "el titulo debe tener entre 2 y 50 caracteres")
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotNull(message = "la descripcion no puede ser nula")
    @NotEmpty(message = "la descripcion no puede quedar vacia")
    @NotBlank(message = "la descripcion no puede quedar vacia")
    @Size(min = 20, max = 2000, message = "la descripcion debe tener entre 20 y 2000 caracteres")
    @Column(name = "descripcion", columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Size(max = 1000, message = "los requisitos no pueden exceder los 1000 caracteres")
    @Column(name = "requisitos", columnDefinition = "TEXT")
    private String requisitos;



}