package com.crusaders.jobKey.DTO.ofertas;

import com.crusaders.jobKey.enums.EModalidad;
import com.crusaders.jobKey.enums.ENivelRequerido;
import com.crusaders.jobKey.enums.ETipoJornada;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class OfertasTrabajoResponse {

    private Integer idOfertaTrabajo;

    private String titulo;
    private String descripcion;
    private String requisitos;
    private BigDecimal salario;

    private EModalidad modalidad;
    private ETipoJornada tipoJornada;
    private ENivelRequerido nivelRequerido;

    private LocalDateTime fechaPublicacion;
    private LocalDate fechaCierre;
    private Boolean activa;

    private Integer empresaId;
    private String nombreEmpresa;

    private Integer departamentoId;
    private String nombreDepartamento;
}