package com.emrsys.medmatrix.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emrsys.medmatrix.entity.ClinicalFindingsEntity;
import com.emrsys.medmatrix.entity.ReferralEntity;
import com.emrsys.medmatrix.object.ClinicalFindingsDto;
import com.emrsys.medmatrix.object.ReferralDto;
import com.emrsys.medmatrix.repository.ClinicalFindingsRepository;
import com.emrsys.medmatrix.repository.ReferralRepository;

import jakarta.transaction.Transactional;

@Service
public class DocumentService {

	@Autowired
	private ReferralRepository referralRepository;

	@Autowired
	private ClinicalFindingsRepository clinicalFindingsRepository;

	@Transactional
	public void saveReferral(ReferralDto referralDto) {
		ReferralEntity referral = new ReferralEntity();
		referral.setPatientId(referralDto.getPatientId());
		referral.setDoctorId(referralDto.getDoctorId());
		referral.setHospitalId(referralDto.getHospitalId());
		referral.setInjuryName(referralDto.getInjuryName());
		referral.setIntroPurpose(referralDto.getIntroPurpose());
		referral.setMedicalHistory(referralDto.getMedicalHistory());
		referral.setFamilyHistory(referralDto.getFamilyHistory());
		referral.setConsultation(referralDto.getConsultation());
		referral.setTreatmentProcess(referralDto.getTreatmentProcess());
		referral.setCurrentPrescription(referralDto.getCurrentPrescription());
		referral.setPhysicalFindings(referralDto.getPhysicalFindings());
		referral.setLifestyleHabits(referralDto.getLifestyleHabits());
		// 创建时自动设置 createdAt，更新时设置 updatedAt
		referral.setUpdatedAt(LocalDateTime.now()); // 每次更新时都要更新

		referralRepository.save(referral);
	}

	@Transactional
	public void saveClinicalFindings(ClinicalFindingsDto clinicalFindingsDto) {
		ClinicalFindingsEntity clinicalFindings = new ClinicalFindingsEntity();
		clinicalFindings.setPatientId(clinicalFindingsDto.getPatientId());
		clinicalFindings.setDoctorId(clinicalFindingsDto.getDoctorId());
		clinicalFindings.setConsultationDate(clinicalFindingsDto.getConsultationDate());
		clinicalFindings.setSubjective(clinicalFindingsDto.getSubjective());
		clinicalFindings.setObjective(clinicalFindingsDto.getObjective());
		clinicalFindings.setAssessment(clinicalFindingsDto.getAssessment());
		clinicalFindings.setPlan(clinicalFindingsDto.getPlan());
		// 创建时自动设置 createdAt，更新时设置 updatedAt
		clinicalFindings.setUpdatedAt(LocalDateTime.now()); // 每次更新时都要更新

		clinicalFindingsRepository.save(clinicalFindings);
	}
}
