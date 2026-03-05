package com.crusaders.jobKey.entity;

import com.crusaders.jobKey.enums.EModalidad;
import com.crusaders.jobKey.enums.ENivelRequerido;
import com.crusaders.jobKey.enums.ETipoJornada;
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
@Entity
@Table(name = "ofertas_trabajo")
public class OfertasTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oferta")
    private Integer idOfertaTrabajo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresas empresa;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamentos departamento;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @NotBlank
    @Size(min = 20, max = 200)
    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Size(max = 100)
    @Column(name = "requisitos", columnDefinition = "TEXT")
    private String requisitos;

    @DecimalMin("0.01")
    @Digits(integer = 8, fraction = 2)
    @Column(name = "salario", precision = 10, scale = 2)
    private BigDecimal salario;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "modalidad", nullable = false)
    private EModalidad modalidad;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_jornada", nullable = false)
    private ETipoJornada tipoJornada;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_requerido", nullable = false)
    private ENivelRequerido nivelRequerido;

    @Column(name = "fecha_publicacion", updatable = false, insertable = false)
    private LocalDateTime fechaPublicacion;

    @NotNull
    @Future
    @Column(name = "fecha_cierre", nullable = false)
    private LocalDate fechaCierre;

    @NotNull
    @Column(name = "activa", nullable = false)
    private Boolean activa = true;
}