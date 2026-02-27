package com.crusaders.jobKey.dto.empresas;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter
public class EmpresasResponse {
    private Integer id;
    private String nombreEmpresa;
    private String email;
    private String telefono;
    private String descripcion;
    private String sectorEmpresarial;
    private Integer departamentoId;
    private String departamentoNombre;
    private boolean tieneLogo;
    private LocalDateTime fechaRegistro;
    private LocalDateTime updatedAt;
}