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
    @NotNull(message = "El usuario es obligatorio")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario", nullable = false)
    private Usuarios usuario;

    /* nombre empresa */
    @NotBlank(message = "El nombre de la empresa es obligatorio")
    @Size(max = 150, message = "El nombre de la empresa no puede superar los 150 caracteres")
    @Column(name = "nombre_empresa", nullable = false, length = 150)
    private String nombreEmpresa;

    /* telefono */
    @Pattern(regexp = "^[0-9]{8,15}$", message = "El teléfono debe contener entre 8 y 15 números")
    @Column(name = "telefono", length = 20)
    private String telefono;

    /* descripcion */
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    /* sector */
    @Size(max = 100, message = "El sector empresarial no puede superar los 100 caracteres")
    @Column(name = "sector_empresarial", length = 100)
    private String sectorEmpresarial;

    /* logo */
    @Lob
    @Column(name = "logo", columnDefinition = "LONGBLOB")
    private byte[] logo;

    /* departamento */
    @NotNull(message = "El departamento es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", referencedColumnName = "id_departamento", nullable = false)
    private Departamentos departamento;

}