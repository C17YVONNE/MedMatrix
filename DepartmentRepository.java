package com.emrsys.medmatrix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emrsys.medmatrix.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer>{

	List<DepartmentEntity> findByHospitalEntityHospitalId(int hospitalId);
}
