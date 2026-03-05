package com.crusaders.jobKey.entity;

import com.crusaders.jobKey.enums.EAdminRole;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonPropertyOrder({
        "idAdmin",
        "nombre",
        "email",
        "passwordHash",
        "rol",
        "ultimoAcceso",
        "fechaRegistro"
})
@Entity
// nombre de la table de nuestra db
@Table(name = "admins")
public class Admins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Integer idAdmin;

    //nombre del admin
    @Column(name = "nombre")
    private String nombre;

    //nombre del email
    @Column(name = "email")
    private String email;

    //hash de la contra
    @Column(name = "password_hash")
    private String passwordHash;

    //nombre del rol
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private EAdminRole rol;

    //ultimo acceso al sistema
    @Column(name = "ultimo_acceso")
    private LocalDateTime ultimoAcceso;

    //cuando se creo el registro
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}