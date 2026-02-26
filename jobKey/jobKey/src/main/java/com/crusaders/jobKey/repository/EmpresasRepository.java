package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresasRepository extends JpaRepository<Empresas, Integer> {
    Empresas findByEmail(String email);
}
