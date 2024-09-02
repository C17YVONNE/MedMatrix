package com.emrsys.medmatrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emrsys.medmatrix.entity.UserLoginEntity;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, Integer> {

}
