package com.emrsys.medmatrix.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emrsys.medmatrix.entity.IntroStatusEntity;
import com.emrsys.medmatrix.entity.PatientEntity;
import com.emrsys.medmatrix.object.IntroStatusDto;
import com.emrsys.medmatrix.object.PatientDto;
import com.emrsys.medmatrix.repository.IntroStatusRepository;
import com.emrsys.medmatrix.repository.PatientInfoRepository;

import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class IntroStatusService {

	@Autowired
	private IntroStatusRepository introStatusRepository;

	@Autowired
	private PatientInfoRepository patientInfoRepository;

	public List<IntroStatusDto> searchIntroStatus(String patientId, LocalDate startDate,
			LocalDate endDate, String keyword) {
		// 使用Specification来动态生成查询条件
		return introStatusRepository.findAll((root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			// 如果用户输入了患者ID
			if (patientId != null && !patientId.isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("patientId"), patientId));
			}

			// 如果用户选择了开始日期
			if (startDate != null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("introDate"), startDate));
			}

			// 如果用户选择了结束日期
			if (endDate != null) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("introDate"), endDate));
			}

			// 如果用户输入了关键字
			if (keyword != null && !keyword.isEmpty()) {
				predicates.add(criteriaBuilder.or(
						criteriaBuilder.like(root.get("patientId"), "%" + keyword + "%"),
						criteriaBuilder.like(root.get("documentId").as(String.class), "%" + keyword + "%"),
						criteriaBuilder.like(root.get("status"), "%" + keyword + "%"),
						criteriaBuilder.like(root.get("introFrom"), "%" + keyword + "%")));
			}

			// 根据所有的条件生成查询
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		}).stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private IntroStatusDto convertToDto(IntroStatusEntity entity) {
		IntroStatusDto dto = new IntroStatusDto();
		dto.setPatientId(entity.getPatientId());
		dto.setDocumentId(entity.getDocumentId());
		dto.setIntroDate(entity.getIntroDate());
		dto.setIntroFrom(entity.getIntroFrom());
		dto.setIntroTo(entity.getIntroTo());
		dto.setStatus(entity.getStatus());

		// 根据 patientId 查询患者信息
		Optional<PatientEntity> patientEntity = patientInfoRepository.findById(entity.getPatientId());
		if (patientEntity.isPresent()) {
			dto.setPatientName(patientEntity.get().getName());
		} else {
			System.out.println("Patient not found for patientId: " + entity.getPatientId()); // 如果找不到患者，输出日志
			dto.setPatientName("不明な患者");
		}

		return dto;
	}

	public PatientDto findPatientInfoByPaitentId(String patientId) {
		Optional<PatientEntity> patientEntity = patientInfoRepository.findById(patientId);
		if (patientEntity.isPresent()) {
			return convertToDto(patientEntity.get());
		} else {
			// 如果找不到患者，可以抛出异常，返回null，或者进行其他错误处理
			throw new NoSuchElementException("IDが " + patientId + "の患者が見つかりませんでした。");
		}
	}

	private PatientDto convertToDto(PatientEntity patientEntity) {
		PatientDto patientDto = new PatientDto();
		patientDto.setPatientId(patientEntity.getPatientId());
		patientDto.setName(patientEntity.getName());
		// 其他字段
		return patientDto;
	}
}
