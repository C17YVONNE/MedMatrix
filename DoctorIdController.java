package com.emrsys.medmatrix.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.emrsys.medmatrix.service.UserLoginService;

@Controller
public class DoctorIdController {

	@Autowired
	private UserLoginService userLoginService;
	
	@GetMapping("/doctorIdGenerator")
	public String showDoctorIdGeneratorPage() {
	    return "doctorIdGenerator";
	}

	@GetMapping("/generateUserInfo")
	public Map<String, String> generateDoctorId() {
		String doctorId = userLoginService.generateDoctorId(); // 实际生成ID的逻辑
		System.out.println("Generated Doctor ID: " + doctorId);
		if (doctorId == null || doctorId.isEmpty()) {
	        doctorId = "DEFAULT_ID"; // 默认值，调试时用
	    }
		// 返回生成的doctorId作为响应
		return Map.of("doctorId", doctorId);
	}
}