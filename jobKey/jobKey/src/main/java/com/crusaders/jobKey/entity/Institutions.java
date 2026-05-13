package com.crusaders.jobKey.entity;

import com.crusaders.jobKey.enums.InstitutionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
// import org.apache.catalina.User;  ← ELIMINA ESTE IMPORT

@Entity
@Table(name = "institutions")
@Data
@NoArgsConstructor
public class Institutions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "institution_id")
    private Integer institutionId;

    @NotNull(message = "The user is required")
    @JoinColumn(name = "user_id", referencedColumnName = "id_usuario", nullable = false)  // ← Cambiar a "id_usuario"
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Usuarios user;  // ← Cambiar a Usuarios (tu entidad)

    @NotBlank(message = "The name of the institution is required")
    @Size(max = 150, message = "The name of the institution must not exceed 150 characters")
    @Column(name = "institution_name", nullable = false, length = 150)
    private String institutionName;

    @Pattern(regexp = "^[0-9]{8,15}$", message = "The phone number must contain between 8 and 15 digits")
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 500, message = "The description cannot exceed 500 characters")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "The institution type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InstitutionType type;

    @Lob
    @Column(name = "logo", columnDefinition = "LONGBLOB")
    private byte[] logo;

    @NotNull(message = "The department is required")
    @JoinColumn(name = "department_id", referencedColumnName = "department_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Departamentos department;
}