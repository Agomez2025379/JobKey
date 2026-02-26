package com.crusaders.jobKey.repository;

import com.crusaders.jobKey.entity.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminsRepository extends JpaRepository <Admins, Integer> {
}
