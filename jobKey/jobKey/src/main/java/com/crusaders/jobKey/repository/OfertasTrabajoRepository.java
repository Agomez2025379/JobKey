package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.OfertasTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertasTrabajoRepository extends JpaRepository<OfertasTrabajo, Integer> {
}