package com.emrsys.medmatrix.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emrsys.medmatrix.object.PatientDto;
import com.emrsys.medmatrix.service.PatientInfoService;
import com.emrsys.medmatrix.util.MedConst;

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

	@GetMapping("/searchPatientId")
	public String searchPatient(@RequestParam(value = "patientId", required = false) String patientId, Model model) {
		if (patientId == null || patientId.trim().isEmpty()) {
			model.addAttribute("warningMessage", "患者IDは必須です！");
			model.addAttribute("patients", new PatientDto()); // 初始化空对象
		} else {
			PatientDto patient = patientInfoService.findPatientInfoByPatientId(patientId);
			if (patient != null) {
				model.addAttribute("patients", patient);
			} else {
				model.addAttribute("infoMessage", "入力した患者IDが存在しません！");
				model.addAttribute("patients", new PatientDto());
			}
		}
		return "searchPatientId";
	}

	@GetMapping("/checkPatientId")
	@ResponseBody
	public Map<String, Boolean> checkPatientId(@RequestParam("patientId") String patientId) {
	    boolean exists = patientInfoService.findPatientInfoByPatientId(patientId) != null;
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("exists", exists);
	    return response;
	}
	    
	@GetMapping("/createPatientInfo")
	public String showCreatePatientForm(Model model) {
		model.addAttribute("patients", new PatientDto());
		return "createPatientInfo";
	}

	@PostMapping("/createPatientInfo")
	public String createPatient(@ModelAttribute("patients") PatientDto patientDto, Model model) {

		PatientDto existingPatient = patientInfoService
				.findPatientInfoByPatientId(patientDto.getPatientId());
		if (existingPatient != null) {
			model.addAttribute("warningMessage", "入力した患者IDがすでに存在します。");
			return "createPatientInfo";
		}

		try {
			patientInfoService.savePatient(patientDto);
			model.addAttribute("successMessage", "患者情報が正常に保存されました。");
			model.addAttribute("patients", patientDto);// 将刚刚保存的患者信息传递到视图
			return "createPatientInfo";

		} catch (Exception e) {
			model.addAttribute("errorMessage", "患者情報の保存中にエラーが発生しました。");
			return "createPatientInfo";// 出现错误时重定向到空白表单
		}
	}
	
	@GetMapping("/updatePatientInfo")
	public String showEditPatientForm(@RequestParam("patientId") String patientId, Model model) {
	    PatientDto patient = patientInfoService.findPatientInfoByPatientId(patientId);
	    if (patient != null) {
	        model.addAttribute("patients", patient);
	        return "updatePatientInfo"; // 返回编辑页面
	    } else {
	        model.addAttribute("errorMessage", "該当する患者情報が見つかりません！");
	        return "redirect:/searchPatientId"; // 如果找不到患者信息，重定向到搜索页面
	    }
	}

	@PostMapping("/updatePatientInfo")
	public String updatePatient(@ModelAttribute("patients") PatientDto patientDto, Model model) {

		PatientDto existingPatient = patientInfoService.findPatientInfoByPatientId(patientDto.getPatientId());
		if (existingPatient == null) {
			model.addAttribute("errorMessage", "更新対象の患者情報が存在しません！");
			return "createPatientInfo";
		}

		try {
			// 保留原有的 created_at 值
	        patientDto.setCreatedAt(existingPatient.getCreatedAt());
			
			patientInfoService.savePatient(patientDto); // 这里仍然使用 save 方法，因为它可以处理保存和更新
			patientDto.setDelFlag(MedConst.DEL_FLAG_ACTIVE);
			model.addAttribute("successMessage", "患者情報が正常に更新されました。");
			model.addAttribute("patients", patientDto); // 将更新后的患者信息传递到视图
			return "createPatientInfo";

		} catch (Exception e) {
			model.addAttribute("errorMessage", "患者情報の更新中にエラーが発生しました。");
			return "createPatientInfo"; // 出现错误时返回表单页面
		}
	}

}