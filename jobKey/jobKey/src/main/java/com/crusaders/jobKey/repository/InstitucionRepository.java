package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, Integer> {

    // Buscar por email
    Optional<Institucion> findByEmail(String email);

    // Verificar si existe email
    boolean existsByEmail(String email);

    // Buscar por tipo
    List<Institucion> findByTipo(Institucion.TipoInstitucion tipo);

    // Buscar por nombre (búsqueda parcial)
    List<Institucion> findByNombreInstitucionContainingIgnoreCase(String nombre);

    // Contar instituciones por tipo
    @Query("SELECT i.tipo, COUNT(i) FROM Institucion i GROUP BY i.tipo")
    List<Object[]> countByTipo();
}
