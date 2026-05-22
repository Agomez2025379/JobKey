package com.crusaders.jobKey.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"idAdmin", "usuario", "nombre"})
@Entity
@Table(name = "admins")
public class Admins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Integer idAdmin;

    @NotNull(message = "Algo salio mal con el usuario")
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Usuarios usuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre")
    private String nombre;
}