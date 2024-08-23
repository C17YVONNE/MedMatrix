package com.emrsys.medmatrix.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emrsys.medmatrix.entity.PatientEntity;
import com.emrsys.medmatrix.object.PatientDto;
import com.emrsys.medmatrix.service.PatientInfoService;

@Controller
public class PatientInfoController {

	@Autowired
	PatientInfoService patientInfoService;

//	@GetMapping("/patientInfo")
//	public String patientInfo(@RequestParam(value = "search", required = false) String search, Model model) {
//
//		List<PatientEntity> patientInfoList;
//
//		if (search != null && !search.isEmpty()) {
//			patientInfoList = patientInfoService.findPatientInfoByName(search);
//			Optional<PatientEntity> patientOpt = patientInfoService.findPatientInfoByPatientId(search);
//			patientOpt.ifPresent(patient -> {
//				if (!patientInfoList.contains(patient)) {
//					patientInfoList.add(0, patient);// Add patientId match at the top
//				}
//			});
//		} else {
//			patientInfoList = patientInfoService.findAllPatientData();
//		}
//		model.addAttribute("pageTitle", "患者情報一覧");
//		model.addAttribute("patientInfoList", patientInfoList);
//
//		return "patientInfo";
//	}

	@GetMapping("/addPatient")
	public String showAddPatientForm(Model model) {
		model.addAttribute("patients", new PatientDto());
		return "addPatient"; // Ensure this returns the correct Thymeleaf template for the form
	}

	@PostMapping("/addPatient")
	public String addPatient(@ModelAttribute PatientDto patientDto, Model model) {
		Optional<PatientEntity> existingPatient = patientInfoService
				.findPatientInfoByPatientId(patientDto.getPatientId());

		if (existingPatient.isPresent()) {
			model.addAttribute("patients", existingPatient.get());
		} else {
			model.addAttribute("errorMessage", "入力した患者IDが存在しません！");
		}
		return "addPatient";
	}
}