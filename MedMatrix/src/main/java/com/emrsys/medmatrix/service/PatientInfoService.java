package com.emrsys.medmatrix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emrsys.medmatrix.entity.PatientEntity;
import com.emrsys.medmatrix.repository.PatientInfoRepository;

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
	public List<PatientEntity> findAllPatientData(){
		return patientInfoRepository.findAll();
	}
	
	/**
	   * 指定した患者IDのレコードを取得する。
	   * @param patientId
	   * @return
	   */
	public Optional<PatientEntity> findPatientInfoByPatientId(String PatientId) {
        return patientInfoRepository.findByPatientId(PatientId);
    }
	
	/**
	   * 指定した患者名前のレコードを取得する。
	   * @param name
	   * @return
	   */
	public List<PatientEntity> findPatientInfoByName(String name) {
        return patientInfoRepository.findByNameContainingIgnoreCase(name);
    }
	
	 public void savePatient(PatientEntity patientEntity) {
		 patientInfoRepository.save(patientEntity);
	    }

	    public void deletePatientByPatientId(String PatientId) {
	    	patientInfoRepository.deleteById(PatientId);
	    }
}
