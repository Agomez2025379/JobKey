package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.Resenas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenasRepository extends JpaRepository<Resenas, Integer> {
}