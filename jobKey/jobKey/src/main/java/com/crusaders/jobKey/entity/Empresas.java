package com.crusaders.jobKey.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter //utilizo ambas anotaciones para no tener q colocar manualmente los getter and setters
@JsonPropertyOrder({
        "id_empresa",
        "nombre_empresa",
        "email",
        "telefono",
        "password_hash",
        "descripcion",               // Coloco el orden que tengo en mi tabla
        "sector_empresarial",
        "logo_url",
        "departamento_id",
        "fecha_registro"
})
@Entity
@Table( name = "empresas")
public class Empresas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @JsonIgnore //Para que no se envie al usuario
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "sector_empresarial")
    private String  sectorEmpresarial;

    @Lob
    @Column(name = "logo_url", columnDefinition = "LONGBLOB")
    private byte[] logoUrl;

    @ManyToOne // Utilizo esto para hacerlo llave foranea
    @JoinColumn(name = "departamento_id") // esto es para decirle q busque departamento_id
    private Departamentos departamentos; // Le asignamos como la clase Departamentos para q sepa q ahi debe buscar


    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


}