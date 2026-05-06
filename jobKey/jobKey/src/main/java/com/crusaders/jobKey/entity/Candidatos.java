package com.crusaders.jobKey.entity;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
        "idCandidato",
        "usuario",
        "nombre",
        "apellido",
        "telefono",
        "profesion",
        "experiencia",
        "educacion",
        "habilidades",
        "curriculumUrl",
        "departamento"
})
@Getter
@Setter
@Entity
@Table(name = "candidatos")
public class Candidatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidato")
    private Integer idCandidato;

    @NotNull(message = "User is mandatory for candidates")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario", nullable = false)
    private Usuarios usuario;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 100, message = "Last name cannot exceed 100 characters")
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    @Pattern(
            regexp = "^[0-9+\\-() ]*$",
            message = "Phone number can only contain numbers and valid symbols"
    )
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Size(max = 100, message = "Profession cannot exceed 100 characters")
    @Column(name = "profesion", length = 100)
    private String profesion;

    @Size(max = 5000, message = "Experience is too long")
    @Column(name = "experiencia", columnDefinition = "TEXT")
    private String experiencia;

    @Size(max = 5000, message = "Education is too long")
    @Column(name = "educacion", columnDefinition = "TEXT")
    private String educacion;

    @Size(max = 5000, message = "Skills are too long")
    @Column(name = "habilidades", columnDefinition = "TEXT")
    private String habilidades;

    @Size(max = 255, message = "Curriculum URL cannot exceed 255 characters")
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "Curriculum URL must be valid"
    )
    @Column(name = "curriculum_url", length = 255)
    private String curriculumUrl;

    @NotNull(message = "Department is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", referencedColumnName = "id_departamento", nullable = false)
    private Departamentos departamento;
}
