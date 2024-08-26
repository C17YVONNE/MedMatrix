package com.emrsys.medmatrix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emrsys.medmatrix.entity.PatientEntity;
import com.emrsys.medmatrix.object.PatientDto;
import com.emrsys.medmatrix.repository.PatientInfoRepository;
import com.emrsys.medmatrix.util.EntityToObjectUtil;
import com.emrsys.medmatrix.util.ObjectToEntityUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PatientInfoService {

	@Autowired
	PatientInfoRepository patientInfoRepository;
	
	/**
	 * レコードを全件取得する。
	 * @return
	 */
	public List<PatientDto> findAllPatientData(){
		List<PatientEntity> entityList = patientInfoRepository.findAll();
		List<PatientDto> dtoList = EntityToObjectUtil.convertEntityListToDtoList(entityList, PatientDto.class);
		return dtoList;
	}
	/**
	   * 指定した患者IDのレコードを取得する。
	   * @param patientId
	   * @return
	   */
	public PatientDto findPatientInfoByPatientId(String patientId) {    
        PatientEntity entity = patientInfoRepository.findByPatientId(patientId);
        return EntityToObjectUtil.convertEntityToDto(entity, PatientDto.class);
    }
	
	/**
	   * 指定した患者名前のレコードを取得する。
	   * @param name
	   * @return
	   */
	public List<PatientDto> findPatientInfoByName(String name) {
		List<PatientEntity> entityList = patientInfoRepository.findByNameContainingIgnoreCase(name);
		List<PatientDto> dtoList = EntityToObjectUtil.convertEntityListToDtoList(entityList, PatientDto.class);
		return dtoList;
    }
	
	 public void savePatient(PatientDto dto) {
		PatientEntity newEntity = ObjectToEntityUtil.convertDtoToEntity(dto, PatientEntity.class);
		 patientInfoRepository.save(newEntity);
	    }

	    public void deletePatientByPatientId(String patientId) {
	    	patientInfoRepository.deleteById(patientId);
	    }
}