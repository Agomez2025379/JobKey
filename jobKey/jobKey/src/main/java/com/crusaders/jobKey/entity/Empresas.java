package com.crusaders.jobKey.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "idEmpresa",
        "usuario",
        "nombreEmpresa",
        "telefono",
        "descripcion",
        "sectorEmpresarial",
        "logo",
        "departamento"
})
@Getter
@Setter
@Entity
@Table(name = "empresas")
public class Empresas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    /* relación con usuario */
    @NotNull(message = "User is mandatory")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario", nullable = false)
    private Usuarios usuario;

    /* nombre empresa */
    @NotBlank(message = "Company name is mandatory")
    @Size(max = 150, message = "Company name cannot exceed 150 characters")
    @Column(name = "nombre_empresa", nullable = false, length = 150)
    private String nombreEmpresa;

    /* telefono */
    @Pattern(regexp = "^[0-9]{8,15}$", message = "Phone number must contain between 8 and 15 digits")
    @Column(name = "telefono", length = 20)
    private String telefono;

    /* descripcion */
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    /* sector */
    @Size(max = 100, message = "Business sector cannot exceed 100 characters")
    @Column(name = "sector_empresarial", length = 100)
    private String sectorEmpresarial;

    /* logo */
    @Lob
    @Column(name = "logo", columnDefinition = "LONGBLOB")
    private byte[] logo;

    /* departamento */
    @NotNull(message = "Department is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", referencedColumnName = "id_departamento", nullable = false)
    private Departamentos departamento;

}