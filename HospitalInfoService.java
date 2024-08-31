package com.emrsys.medmatrix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emrsys.medmatrix.entity.DepartmentEntity;
import com.emrsys.medmatrix.entity.HospitalEntity;
import com.emrsys.medmatrix.object.DepartmentDto;
import com.emrsys.medmatrix.object.HospitalDto;
import com.emrsys.medmatrix.repository.DepartmentRepository;
import com.emrsys.medmatrix.repository.HospitalRepository;
import com.emrsys.medmatrix.util.EntityToObjectUtil;

import jakarta.transaction.Transactional;

@Service
public class HospitalInfoService {

	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<HospitalDto> getAllHospitals(){
		List<HospitalEntity> HospitalEntityList = hospitalRepository.findAll();
		return EntityToObjectUtil.convertEntityListToDtoList(HospitalEntityList, HospitalDto.class);
	}
	
	public List<DepartmentDto> getAllDepartsByHospitalId(int hospitalId){
		List<DepartmentEntity> DepartEntityList= departmentRepository.findByHospitalEntityHospitalId(hospitalId);
		return EntityToObjectUtil.convertEntityListToDtoList(DepartEntityList, DepartmentDto.class);
	}
	
	@Transactional
	public void addHospitalInfo(String hospitalName, String postcode, String address, String number) {
		
		HospitalEntity entity = new HospitalEntity();
		entity.setHospitalName(hospitalName);
		entity.setPostcode(postcode);
		entity.setAddress(address);
		entity.setNumber(number);
		
		hospitalRepository.save(entity);
	}
	
}
