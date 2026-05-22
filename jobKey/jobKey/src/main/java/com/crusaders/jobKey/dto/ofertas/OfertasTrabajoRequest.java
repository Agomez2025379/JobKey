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

    @NotNull(message = "Company ID is required")
    private Integer empresaId;

    @NotNull(message = "Department ID is required")
    private Integer departamentoId;

    @NotBlank(message = "Title is required")
    @Size(
            min = 2,
            max = 50,
            message = "Title must be between 2 and 50 characters"
    )
    private String titulo;

    @NotBlank(message = "Description is required")
    @Size(
            min = 20,
            max = 200,
            message = "Description must be between 20 and 200 characters"
    )
    private String descripcion;

    @Size(
            max = 100,
            message = "Requirements cannot exceed 100 characters"
    )
    private String requisitos;

    @DecimalMin(
            value = "0.01",
            message = "Salary must be greater than 0"
    )
    @Digits(
            integer = 8,
            fraction = 2,
            message = "Salary format is invalid"
    )
    private BigDecimal salario;

    @NotNull(message = "Work modality is required")
    private EModalidad modalidad;

    @NotNull(message = "Work schedule is required")
    private ETipoJornada tipoJornada;

    @NotNull(message = "Required level is required")
    private ENivelRequerido nivelRequerido;

    @NotNull(message = "Closing date is required")
    @Future(message = "Closing date must be in the future")
    private LocalDate fechaCierre;
}