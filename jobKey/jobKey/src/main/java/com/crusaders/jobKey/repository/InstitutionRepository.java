package com.crusaders.jobKey.repository;


import com.crusaders.jobKey.entity.Institutions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institutions, Integer> {

    Institutions findByUser_UserId(Integer userId);

}
