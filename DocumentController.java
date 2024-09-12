package com.emrsys.medmatrix.controller;

import java.sql.Date;
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
import com.emrsys.medmatrix.service.UserLoginService;
import com.emrsys.medmatrix.util.MedConst;
import com.emrsys.medmatrix.util.UserInfoSession;

import jakarta.servlet.http.HttpSession;

@Controller
public class DocumentController extends BasePageController {

	@Autowired
	private HospitalInfoService hospitalInfoService;

	@Autowired
	private PatientInfoService patientInfoService;

	@Autowired
	private DocumentService documentService;

	@Autowired
	UserLoginService userLoginService;

	@GetMapping("/documentForm")
	public ModelAndView showDocumentForm(@RequestParam("patientId") String patientId, HttpSession session,
			Model model) {
		// 从 session 获取 doctorId
		UserInfoSession userInfoSession = (UserInfoSession) session.getAttribute("userInfoSession");
		if (userInfoSession == null || userInfoSession.getDoctorId() < MedConst.DOCTORID_START_FROM) {
			throw new IllegalArgumentException("Invalid doctorId");
		}

		PatientDto patientdto = patientInfoService.findPatientInfoByPatientId(patientId); // 通过ID获取患者信息
		if (patientdto == null) {
			model.addAttribute("errorMessage", "患者情報が見つかりません。");
			model.addAttribute("patients", new PatientDto()); // 初始化空对象
			return createMav("searchPatientId", session);
		}
		model.addAttribute("patient", patientdto);

		// 把醫院列表加到模型中
		List<HospitalDto> hospitals = hospitalInfoService.getAllHospitals();
		model.addAttribute("hospitals", hospitals);

		ClinicalFindingsDto clinicalFindingsDto = new ClinicalFindingsDto();

		model.addAttribute("clinicalFindingsDto", clinicalFindingsDto);

		// 确保 doctorId 正确传递到前端
		model.addAttribute("userInfoSession", userInfoSession);

		return createMav("documentForm", session);
	}

	@ResponseBody
	@GetMapping("/departments")
	public List<DepartmentDto> getDepartmentsByHospital(@RequestParam int hospitalId) {
		return hospitalInfoService.getAllDepartsByHospitalId(hospitalId);
	}

	@GetMapping("/docSaveSuccess")
	public String showSaveSuccessPage() {
		return "docSaveSuccess";
	}

	@PostMapping("/saveReferral")
	public ModelAndView saveReferral(@ModelAttribute ReferralDto referralDto, HttpSession session, Model model) {
		documentService.saveReferral(referralDto);

		// 重新获取 patient 对象并传递给视图
		PatientDto patientdto = patientInfoService.findPatientInfoByPatientId(referralDto.getPatientId());
		if (patientdto == null) {
			model.addAttribute("errorMessage", "患者情報が見つかりません。");
			return createMav("searchPatientId", session);
		}
		model.addAttribute("patient", patientdto);

		// 重新设置 session 信息
		UserInfoSession userInfoSession = userLoginService.getLoginSession(referralDto.getDoctorId());
		session.setAttribute("userInfoSession", userInfoSession);

		// 显示成功信息
		model.addAttribute("successMessage", "紹介状が正常に保存されました。");
		return createMav("docSaveSuccess", session);
	}

	@PostMapping("/saveClinicalFindings")
	public ModelAndView saveClinicalFindings(@ModelAttribute @RequestParam("patientId") String patientId,
			@RequestParam("doctorId") int doctorId,
			@RequestParam("consultationDate") Date consultationDate,
			@RequestParam("subjective") String subjective,
			@RequestParam("objective") String objective,
			@RequestParam("assessment") String assessment,
			@RequestParam("plan") String plan,
			HttpSession session, Model model) {
		if (doctorId < MedConst.DOCTORID_START_FROM) {
			throw new IllegalArgumentException("Invalid doctorId");
		}

		documentService.saveClinicalFindings(patientId, doctorId, consultationDate, subjective, objective, assessment,
				plan);

		// 重新获取 patient 对象并传递给视图
		PatientDto patientdto = patientInfoService.findPatientInfoByPatientId(patientId);
		if (patientdto == null) {
			model.addAttribute("errorMessage", "患者情報が見つかりません。");
			return createMav("searchPatientId", session);
		}
		model.addAttribute("patient", patientdto);

		// 重新设置 session 信息
		UserInfoSession userInfoSession = userLoginService.getLoginSession(doctorId);
		session.setAttribute("userInfoSession", userInfoSession);

		// 显示成功信息
		model.addAttribute("successMessage", "診療所見が正常に保存されました。");
		return createMav("docSaveSuccess", session);
	}
}
