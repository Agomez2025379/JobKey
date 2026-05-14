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

    @NotNull(message = "User is required")
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Usuarios usuario;

    @NotBlank(message = "Institution name is required")
    @Size(max = 150, message = "Institution name cannot exceed 150 characters")
    @Column(name = "nombre_institucion", nullable = false, length = 150)
    private String nombreInstitucion;

    @Pattern(regexp = "^[0-9]{8,15}$", message = "Phone number must contain between 8 and 15 digits")
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "Institution type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ETipoInstitucion tipo;

    @Lob
    @Column(name = "logo", columnDefinition = "LONGBLOB")
    private byte[] logo;

    @NotNull(message = "Department is required")
    @JoinColumn(name = "departamento_id", referencedColumnName = "id_departamento", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Departamentos departamento;

}