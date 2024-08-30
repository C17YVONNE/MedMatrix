package com.emrsys.medmatrix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emrsys.medmatrix.entity.UserLoginEntity;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, String> {
	Optional<UserLoginEntity> findByDoctorId(String doctorId);

	boolean existsByDoctorId(String doctorId);
}
