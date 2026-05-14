package com.crusaders.jobKey.DTO.instituciones;

import com.crusaders.jobKey.enums.ETipoInstitucion;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InstitucionResponse {

    private Integer idInstitucion;
    private Integer usuarioId;
    private String nombreInstitucion;
    private String telefono;
    private String descripcion;
    private ETipoInstitucion tipo;
    private Integer departamentoId;
}