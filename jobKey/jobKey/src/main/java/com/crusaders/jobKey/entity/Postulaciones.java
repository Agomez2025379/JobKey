package com.crusaders.jobKey.entity;

import com.crusaders.jobKey.enums.EEstado;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "postulaciones",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_postulacion",
                        columnNames = {"oferta_id", "candidato_id"}
                )
        }
)
@JsonPropertyOrder({
        "idPostulacion",
        "ofertaTrabajo",
        "candidato",
        "fechaPostulacion",
        "estado",
        "comentarios"
})
public class Postulaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postulacion", updatable = false, nullable = false)
    private Integer idPostulacion;

    @NotNull(message = "La oferta de trabajo es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "oferta_id", nullable = false)
    private OfertasTrabajo ofertaTrabajo;

    @NotNull(message = "El candidato es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidato_id", nullable = false)
    private Candidatos candidato;

    @NotNull(message = "La fecha de postulación es obligatoria")
    @Column(name = "fecha_postulacion", nullable = false, updatable = false)
    private LocalDateTime fechaPostulacion;

    @NotNull(message = "El estado de la postulación es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EEstado estado;

    @Size(max = 2000, message = "Los comentarios no pueden superar 2000 caracteres")
    @Column(name = "comentarios", columnDefinition = "TEXT")
    private String comentarios;

    @PrePersist
    private void prePersist() {
        if (fechaPostulacion == null) {
            fechaPostulacion = LocalDateTime.now();
        }

        if (estado == null) {
            estado = EEstado.PENDIENTE;
        }
    }
}