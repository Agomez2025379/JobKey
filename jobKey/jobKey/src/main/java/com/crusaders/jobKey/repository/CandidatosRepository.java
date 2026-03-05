package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.Candidatos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidatosRepository extends JpaRepository<Candidatos, Integer> {
    Candidatos findByNombre(String nombre);

    Optional<Candidatos> findByEmail(String email);
    boolean existsByEmail(String email);
}