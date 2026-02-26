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
        "id_candidato",
        "nombre",
        "apellido",
        "email",
        "telefono",
        "password_hash",
        "profesion",                      // Coloco el orden que tengo en mi tabla
        "experiencia",
        "educacion",
        "habilidades",
        "curriculum_url",
        "departamento_id",
        "fecha_registro"})
@Entity
@Table( name = "candidatos")
public class Candidatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidato")
    private Integer idCandidato;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @JsonIgnore //Para que no se envie al usuario
    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "profesion")
    private String profesion;

    @Column(name = "experiencia")
    private String experiencia;

    @Column(name = "educacion")
    private String eduacion;

    @Column(name = "habilidades")
    private String habilidades;

    @Column(name = "curriculum_url")
    private String curriculumUrl;

    @ManyToOne // Para hacerlo llave foranea utilizo la relacion muchos a uno
    @JoinColumn(name = "departamento_id") // esto es para decirle q busque
    private Departamentos departamentos;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
