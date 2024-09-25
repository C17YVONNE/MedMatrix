package com.emrsys.medmatrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emrsys.medmatrix.entity.MedicineEntity;
import com.emrsys.medmatrix.object.MedicineOjt;
import com.emrsys.medmatrix.repository.MedicineRepository;

@Service
public class MedicineInfoService {

	@Autowired
	private MedicineRepository medicineRepository;

	public void saveMedicine(MedicineOjt medicineOjt) {
		MedicineEntity medEntity = new MedicineEntity();
		medEntity.setName(medicineOjt.getName());
		medEntity.setDescription(medicineOjt.getDescription());
		medEntity.setPrice(medicineOjt.getPrice());
		medEntity.setPicLink(medicineOjt.getPicLink());

		medicineRepository.save(medEntity);
	}
}
