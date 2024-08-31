package com.emrsys.medmatrix.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emrsys.medmatrix.entity.DoctorInfoEntity;
import com.emrsys.medmatrix.repository.DoctorInfoRepository;
import com.emrsys.medmatrix.util.MedConst;

import jakarta.transaction.Transactional;

@Service
public class DoctorInfoService {

	@Autowired
	DoctorInfoRepository doctorInfoRepository;

	@Transactional
	public void addDoctorInfo(String doctorName, int hospitalId, int departmentId) {

		DoctorInfoEntity entity = new DoctorInfoEntity();
		entity.setDoctorId("zhao002");
		entity.setName(doctorName);
		entity.setHospitalId(hospitalId);
		entity.setDepartmentId(departmentId);

		entity.setDelFlag(MedConst.DOCTORINFO_DEL_FLAG_ACTIVE);
		LocalDateTime currentTime = LocalDateTime.now();
		entity.setCreatedAt(currentTime);
		entity.setUpdatedAt(currentTime);

		doctorInfoRepository.save(entity);
	}

}
