package com.emrsys.medmatrix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emrsys.medmatrix.entity.PatientEntity;

@Repository
public interface PatientInfoRepository extends JpaRepository<PatientEntity, String>{
	List<PatientEntity> findAll();
	PatientEntity findByPatientId(String patientId);
	List<PatientEntity> findByNameContainingIgnoreCase(String name);
}
