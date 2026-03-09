package com.crusaders.jobKey.DTO.empresas;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmpresasResponse {

    private Integer idEmpresa;
    private Integer usuarioId;
    private String nombreEmpresa;
    private String telefono;
    private String descripcion;
    private String sectorEmpresarial;
    private Integer departamentoId;

}