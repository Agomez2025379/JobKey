package com.crusaders.jobKey.entity;

import com.crusaders.jobKey.entity.enums.EModalidad;
import com.crusaders.jobKey.entity.enums.ENivelRequerido;
import com.crusaders.jobKey.entity.enums.ETipoJornada;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({
        "idOfertaTrabajo",
        "empresa",
        "titulo",
        "descripcion",
        "requisitos",
        "salario",
        "modalidad",
        "tipoJornada",
        "nivelRequerido",
        "departamento",
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresas empresa;

    @NotNull(message = "el departamento no puede ser nulo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamentos departamento;

    @NotBlank(message = "el titulo no puede estar vacio")
    @Size(min = 2, max = 50, message = "el titulo debe tener entre 2 y 50 caracteres")
    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @NotBlank(message = "la descripcion no puede estar vacia")
    @Size(min = 20, max = 2000, message = "la descripcion debe tener entre 20 y 2000 caracteres")
    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Size(max = 1000, message = "los requisitos no pueden exceder 1000 caracteres")
    @Column(name = "requisitos", columnDefinition = "TEXT")
    private String requisitos;

    @DecimalMin(value = "0.01", message = "el salario debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "maximo 8 enteros y 2 decimales")
    @Column(name = "salario", precision = 10, scale = 2)
    private BigDecimal salario;

    @NotNull(message = "la modalidad no puede ser nula")
    @Enumerated(EnumType.STRING)
    @Column(name = "modalidad", nullable = false)
    private EModalidad modalidad;

    @NotNull(message = "el tipo de jornada no puede ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_jornada", nullable = false)
    private ETipoJornada tipoJornada;

    @NotNull(message = "el nivel requerido no puede ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_requerido", nullable = false)
    private ENivelRequerido nivelRequerido;

    @Column(name = "fecha_publicacion", updatable = false, insertable = false)
    private LocalDateTime fechaPublicacion;

    @NotNull(message = "la fecha de cierre no puede ser nula")
    @Future(message = "la fecha de cierre debe ser futura")
    @Column(name = "fecha_cierre", nullable = false)
    private LocalDate fechaCierre;

    @NotNull(message = "el estado activo no puede ser nulo")
    @Column(name = "activa", nullable = false)
    private Boolean activa = true;
}