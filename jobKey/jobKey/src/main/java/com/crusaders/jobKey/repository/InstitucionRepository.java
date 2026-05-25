package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InstitucionRepository extends JpaRepository<Institucion, Integer> {

    List<Institucion> findByUsuario_IdUsuario(Integer idUsuario);

    Optional<Institucion> findFirstByUsuario_IdUsuario(Integer idUsuario);

}