package com.crusaders.jobKey.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Getter
@NoArgsConstructor
@Setter
@Immutable
@JsonPropertyOrder({
        "idDepartamento",
        "departamento"
})
@Data
@Entity
@Table(name = "departamentos")
public class Departamentos {

    @Id
    @Column(name = "id_departamento", updatable = false, nullable = false)
    private Integer idDepartamento;

    @Column(name = "nombre", nullable = false, length = 100, updatable = false)
    private String departamento;
}
