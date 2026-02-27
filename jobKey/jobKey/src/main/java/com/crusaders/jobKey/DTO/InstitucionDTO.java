package com.crusaders.jobKey.DTO;

import com.crusaders.jobKey.entity.Institucion;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class InstitucionDTO {

    private Integer id;

    @NotBlank(message = "El nombre de la institución es obligatorio")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    private String nombreInstitucion;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe proporcionar un email válido")
    private String email;

    @Pattern(regexp = "^[0-9]{8}$", message = "El teléfono debe tener 8 dígitos")
    private String telefono;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;

    @NotNull(message = "Debe seleccionar el tipo de institución")
    private String tipo;



    // Convertir DTO a Entidad
    public Institucion toEntity() {
        Institucion institucion = new Institucion();
        institucion.setId(this.id);
        institucion.setNombreInstitucion(this.nombreInstitucion);
        institucion.setEmail(this.email);
        institucion.setTelefono(this.telefono);
        institucion.setPassword(this.password);
        institucion.setDescripcion(this.descripcion);

        if (this.tipo != null) {
            institucion.setTipo(Institucion.TipoInstitucion.valueOf(this.tipo));
        }

        return institucion;
    }

    // Validación para nuevo registro
    @AssertTrue(message = "La contraseña es obligatoria para nuevos registros")
    public boolean isPasswordValidaParaRegistro() {
        return id != null || (password != null && password.length() >= 6);
    }
}
