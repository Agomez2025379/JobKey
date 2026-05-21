package com.crusaders.jobKey.dto.ofertas;

import com.crusaders.jobKey.enums.EModalidad;
import com.crusaders.jobKey.enums.ENivelRequerido;
import com.crusaders.jobKey.enums.ETipoJornada;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class OfertasTrabajoRequest {

    @NotNull
    private Integer empresaId;

    @NotNull
    private Integer departamentoId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String titulo;

    @NotBlank
    @Size(min = 20, max = 200)
    private String descripcion;

    @Size(max = 100)
    private String requisitos;

    @DecimalMin("0.01")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal salario;

    @NotNull
    private EModalidad modalidad;

    @NotNull
    private ETipoJornada tipoJornada;

    @NotNull
    private ENivelRequerido nivelRequerido;

    @NotNull
    @Future
    private LocalDate fechaCierre;
}