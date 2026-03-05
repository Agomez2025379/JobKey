package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.Candidatos;
import com.crusaders.jobKey.entity.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresasRepository extends JpaRepository<Empresas, Integer> {
    Optional<Empresas> findByEmail(String email);
    boolean existsByEmail(String email);
}
