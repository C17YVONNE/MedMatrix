package com.emrsys.medmatrix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emrsys.medmatrix.object.DepartmentDto;
import com.emrsys.medmatrix.object.HospitalDto;
import com.emrsys.medmatrix.object.PatientDto;
import com.emrsys.medmatrix.service.HospitalInfoService;
import com.emrsys.medmatrix.service.PatientInfoService;

@Controller
public class DocumentController {

	@Autowired
	private HospitalInfoService hospitalInfoService;

	@Autowired
	private PatientInfoService patientInfoService;

	@GetMapping("/documentForm")
	public String showDocumentForm(@RequestParam("patientId") String patientId, Model model) {

		PatientDto patientdto = patientInfoService.findPatientInfoByPatientId(patientId); // 通过ID获取患者信息
		if (patientdto != null) {
			model.addAttribute("patient", patientdto);
		} else {
			model.addAttribute("errorMessage", "患者情報が見つかりませんでした。");
			return "searchPatientInfo"; // 如果患者信息未找到，则返回搜索页面
		}

		List<HospitalDto> hospitals = hospitalInfoService.getAllHospitals();
		model.addAttribute("hospitals", hospitals);
		return "documentForm";
	}

	@ResponseBody
	@GetMapping("/departments")
	public List<DepartmentDto> getDepartmentsByHospital(@RequestParam int hospitalId) {
		return hospitalInfoService.getAllDepartsByHospitalId(hospitalId);
	}
}
