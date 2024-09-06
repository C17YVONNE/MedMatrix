package com.emrsys.medmatrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emrsys.medmatrix.entity.ClinicalFindingsEntity;

@Repository
public interface ClinicalFindingsRepository extends JpaRepository<ClinicalFindingsEntity, Long> {

}
