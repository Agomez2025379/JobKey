package com.crusaders.jobKey.dto.instituciones;


import com.crusaders.jobKey.enums.ETipoInstitucion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitucionRequest {

    private Integer usuarioId;       // id_usuario existente
    private String nombreInstitucion;
    private String telefono;
    private String descripcion;
    private ETipoInstitucion tipo;
    private byte[] logo;
    private Integer departamentoId;
}