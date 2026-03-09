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

    @NotNull(message = "El usuario es obligatorio para candidatos")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario", nullable = false)
    private Usuarios usuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El apellido no puede superar 100 caracteres")
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Size(max = 20, message = "El teléfono no puede superar 20 caracteres")
    @Pattern(
            regexp = "^[0-9+\\-() ]*$",
            message = "El teléfono solo puede contener números y símbolos válidos"
    )
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Size(max = 100, message = "La profesión no puede superar 100 caracteres")
    @Column(name = "profesion", length = 100)
    private String profesion;

    @Size(max = 5000, message = "La experiencia es demasiado larga")
    @Column(name = "experiencia", columnDefinition = "TEXT")
    private String experiencia;

    @Size(max = 5000, message = "La educación es demasiado larga")
    @Column(name = "educacion", columnDefinition = "TEXT")
    private String educacion;

    @Size(max = 5000, message = "Las habilidades son demasiado largas")
    @Column(name = "habilidades", columnDefinition = "TEXT")
    private String habilidades;

    @Size(max = 255, message = "La URL del curriculum no puede superar 255 caracteres")
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "La URL del currículum debe ser válida"
    )
    @Column(name = "curriculum_url", length = 255)
    private String curriculumUrl;

    @NotNull(message = "El departamento es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", referencedColumnName = "id_departamento", nullable = false)
    private Departamentos departamento;
}
