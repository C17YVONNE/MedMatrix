package com.emrsys.medmatrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emrsys.medmatrix.entity.MedicineEntity;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, Long> {

}
