package com.crusaders.jobKey.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "instituciones")
@Data
@NoArgsConstructor
public class Institucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Integer id;

    @Column(name = "nombre_institucion", nullable = false, length = 150)
    private String nombreInstitucion;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(length = 20)
    private String telefono;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoInstitucion tipo;

    @Column(name = "logo_url", columnDefinition = "LONGBLOB")
    private byte[] logoUrl;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
    }

    public enum TipoInstitucion {
        universidad, instituto, bootcamp, colegio
    }
}

