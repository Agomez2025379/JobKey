package com.crusaders.jobKey.dto.empresas;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmpresasRequest {
    @NotBlank @Size(max = 150)
    private String nombreEmpresa;
    @NotBlank @Email @Size(max = 100)
    private String email;
    @NotBlank @Size(min = 8, max = 255)
    private String password;
    @Size(max = 20)
    private String telefono;
    private String descripcion;
    @Size(max = 100)
    private String sectorEmpresarial;
    private Integer departamentoId;
}
