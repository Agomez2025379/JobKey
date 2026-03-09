package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.Admins;
import com.crusaders.jobKey.entity.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdminsRepository extends JpaRepository<Admins, Integer> {
    Admins findByUsuario_IdUsuario(Integer idUsuario);
}

