package com.crusaders.jobKey.dto.instituciones;

import com.crusaders.jobKey.enums.ETipoInstitucion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitucionRequest {

<<<<<<< HEAD
=======
    private Integer idInstitucion;
    private String email;
>>>>>>> edc3e7a67fd99a8f64f281b97ff1efb37eaa63db
    private Integer usuarioId;
    private String nombreInstitucion;
    private String telefono;
    private String descripcion;
    private ETipoInstitucion tipo;
    private byte[] logo;
    private Integer departamentoId;
}