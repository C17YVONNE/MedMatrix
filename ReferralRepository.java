package com.emrsys.medmatrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emrsys.medmatrix.entity.ReferralEntity;

@Repository
public interface ReferralRepository extends JpaRepository<ReferralEntity, Long>{

}
