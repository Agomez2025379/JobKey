package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresasRepository extends JpaRepository<Empresas, Integer> {

    Optional<Empresas> findByUsuario(Integer usuarioId);

    Empresas findByUsuario_IdUsuario(Integer idUsuario);
}