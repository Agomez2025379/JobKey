package com.crusaders.jobKey.entity;

import com.crusaders.jobKey.enums.ETipoInstitucion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instituciones")
@Data
@NoArgsConstructor
public class Institucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Integer idInstitucion;

    @NotNull(message = "El usuario es obligatorio")
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Usuarios usuario;

    @NotBlank(message = "El nombre de la institución es obligatorio")
    @Size(max = 150, message = "El nombre de la institución no puede superar los 150 caracteres")
    @Column(name = "nombre_institucion", nullable = false, length = 150)
    private String nombreInstitucion;

    @Pattern(regexp = "^[0-9]{8,15}$", message = "El teléfono debe contener entre 8 y 15 números")
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "El tipo de institución es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ETipoInstitucion tipo;

    @Lob
    @Column(name = "logo", columnDefinition = "LONGBLOB")
    private byte[] logo;

    @NotNull(message = "El departamento es obligatorio")
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Departamentos departamento;

}

