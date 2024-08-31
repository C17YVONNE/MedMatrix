package com.emrsys.medmatrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emrsys.medmatrix.entity.DoctorInfoEntity;

public interface DoctorInfoRepository extends JpaRepository<DoctorInfoEntity, String>{

}
