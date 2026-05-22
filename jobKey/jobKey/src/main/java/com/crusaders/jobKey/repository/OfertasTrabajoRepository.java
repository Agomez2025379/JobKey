package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.OfertasTrabajo;
import com.crusaders.jobKey.enums.EModalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertasTrabajoRepository extends JpaRepository<OfertasTrabajo, Integer> {

    @Query("""
                SELECT o FROM OfertasTrabajo o
                WHERE (:keyword IS NULL OR LOWER(o.titulo) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(o.descripcion) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(o.empresa.nombreEmpresa) LIKE LOWER(CONCAT('%', :keyword, '%')))
                AND (:departamentoId IS NULL OR o.departamento.idDepartamento = :departamentoId)
                AND (:modalidad IS NULL OR o.modalidad = :modalidad)
            """)

    List<OfertasTrabajo> filtrar(@Param("keyword") String keyword,
                                 @Param("departamentoId") Integer departamentoId,
                                 @Param("modalidad") EModalidad modalidad);
}