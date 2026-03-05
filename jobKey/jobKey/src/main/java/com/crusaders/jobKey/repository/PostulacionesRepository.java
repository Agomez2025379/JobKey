package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.Postulaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostulacionesRepository extends JpaRepository<Postulaciones, Integer> {

    boolean existsByOfertaTrabajoIdOfertaTrabajoAndCandidatoIdCandidato(
            Integer ofertaId,
            Integer candidatoId
    );

}