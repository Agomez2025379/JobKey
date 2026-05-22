package com.crusaders.jobKey.entity;

import com.crusaders.jobKey.enums.EUsuarioRol;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private EUsuarioRol rol;

    @PastOrPresent
    @Column(name = "ultimo_acces")
    private LocalDateTime ultimoAcceso;

    @CreationTimestamp
    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    public Usuarios() {}

    public Usuarios(
            String passwordHash,
            String email,
            EUsuarioRol rol,
            LocalDateTime ultimoAcceso,
            LocalDateTime fechaRegistro
    )
    {
        this.passwordHash = passwordHash;
        this.email = email;
        this.rol = rol;
        this.ultimoAcceso = ultimoAcceso;
        this.fechaRegistro = fechaRegistro;
    }

    public Usuarios(
            String email,
            String passwordHash,
            EUsuarioRol rol
    )
    {
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = rol;
    }
}
