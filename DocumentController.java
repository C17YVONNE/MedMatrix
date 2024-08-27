package com.emrsys.medmatrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emrsys.medmatrix.object.PatientDto;
import com.emrsys.medmatrix.service.PatientInfoService;

@Controller
public class DocumentController {
	
	@Autowired
	PatientInfoService patientInfoService;
	
	@GetMapping("/documentForm")
    public String showDocumentForm(@RequestParam("patientId") String patientId, Model model) {
        
		PatientDto patientdto = patientInfoService.findPatientInfoByPatientId(patientId); // 通过ID获取患者信息
        if (patientdto != null) {
            model.addAttribute("patient", patientdto);
            return "documentForm";
        } else {
            model.addAttribute("errorMessage", "患者情報が見つかりませんでした。");
            return "searchPatientInfo"; // 如果患者信息未找到，则返回搜索页面
        }
    }
}
