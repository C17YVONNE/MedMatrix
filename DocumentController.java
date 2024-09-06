package com.emrsys.medmatrix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emrsys.medmatrix.object.ClinicalFindingsDto;
import com.emrsys.medmatrix.object.DepartmentDto;
import com.emrsys.medmatrix.object.HospitalDto;
import com.emrsys.medmatrix.object.PatientDto;
import com.emrsys.medmatrix.object.ReferralDto;
import com.emrsys.medmatrix.service.DocumentService;
import com.emrsys.medmatrix.service.HospitalInfoService;
import com.emrsys.medmatrix.service.PatientInfoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DocumentController extends BasePageController {

	@Autowired
	private HospitalInfoService hospitalInfoService;

	@Autowired
	private PatientInfoService patientInfoService;

	@Autowired
	private DocumentService documentService;

	@GetMapping("/documentForm")
	public ModelAndView showDocumentForm(@RequestParam("patientId") String patientId, HttpSession session,
			Model model) {

		PatientDto patientdto = patientInfoService.findPatientInfoByPatientId(patientId); // 通过ID获取患者信息
		if (patientdto != null) {
			model.addAttribute("patient", patientdto);
		} else {
			model.addAttribute("errorMessage", "患者情報が見つかりませんでした。");
			return createMav("searchPatientInfo", session); // 如果患者信息未找到，则返回搜索页面
		}

		List<HospitalDto> hospitals = hospitalInfoService.getAllHospitals();
		model.addAttribute("hospitals", hospitals);
		return createMav("documentForm", session);
	}

	@ResponseBody
	@GetMapping("/departments")
	public List<DepartmentDto> getDepartmentsByHospital(@RequestParam int hospitalId) {
		return hospitalInfoService.getAllDepartsByHospitalId(hospitalId);
	}

	@PostMapping("/saveReferral")
	public String saveReferral(@ModelAttribute ReferralDto referralDto, Model model) {
		documentService.saveReferral(referralDto);
		model.addAttribute("successMessage", "紹介状が正常に保存されました。");
		return "documentForm";
	}

	@PostMapping("/saveClinicalFindings")
	public String saveClinicalFindings(@ModelAttribute ClinicalFindingsDto clinicalFindingsDto, Model model) {
		documentService.saveClinicalFindings(clinicalFindingsDto);
		model.addAttribute("successMessage", "診療所見が正常に保存されました。");
		return "documentForm";
	}
}
