package com.crusaders.jobKey.dto.empresas;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresasRequest {

    private Integer usuarioId;
    private String nombreEmpresa;
    private String telefono;
    private String descripcion;
    private String sectorEmpresarial;
    private byte[] logo;
    private Integer departamentoId;

}
