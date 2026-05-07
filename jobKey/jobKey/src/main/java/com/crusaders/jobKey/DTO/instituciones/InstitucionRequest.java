package com.crusaders.jobKey.DTO.instituciones;


import com.crusaders.jobKey.enums.InstitutionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitucionRequest {

    private Integer usuarioId;       // id_usuario existente
    private String nombreInstitucion;
    private String telefono;
    private String descripcion;
    private InstitutionType tipo;
    private byte[] logo;
    private Integer departamentoId;
}