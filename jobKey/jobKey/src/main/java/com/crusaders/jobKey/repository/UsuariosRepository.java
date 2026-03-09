package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    Optional<Usuarios> findByEmail(String email);

    boolean existsByEmail(String email);
}