// AdminResponse.java
package com.crusaders.jobKey.dto.admins;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AdminResponse {
    private Integer idAdmin;
    private String nombre;
    private String email;
    private LocalDateTime ultimoAcceso;
    private LocalDateTime fechaRegistro;
}