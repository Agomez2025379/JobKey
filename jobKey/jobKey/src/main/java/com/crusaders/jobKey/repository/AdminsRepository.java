package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminsRepository extends JpaRepository<Admins, Integer> {
    Optional<Admins> findByEmail(String email);
    boolean existsByEmail(String email);
}

