package com.emrsys.medmatrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.emrsys.medmatrix.entity.IntroStatusEntity;

@Repository
public interface IntroStatusRepository
		extends JpaRepository<IntroStatusEntity, Long>, JpaSpecificationExecutor<IntroStatusEntity> {
}
