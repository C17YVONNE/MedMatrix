package com.emrsys.medmatrix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emrsys.medmatrix.object.DepartmentDto;
import com.emrsys.medmatrix.object.HospitalDto;
import com.emrsys.medmatrix.service.DoctorInfoService;
import com.emrsys.medmatrix.service.HospitalInfoService;
import com.emrsys.medmatrix.service.UserLoginService;

@Controller
public class DoctorInfoController {

	@Autowired
	private UserLoginService userLoginService;

	@Autowired
	private HospitalInfoService hospitalInfoService;
	
	@Autowired
	DoctorInfoService doctorInfoService;

	@GetMapping("/addDoctorInfo")
	public ModelAndView ShowAddDoctorInfoForm(Model model) {

		List<HospitalDto> hospitalDtoList = hospitalInfoService.getAllHospitals();

		model.addAttribute("hospitalDtoList", hospitalDtoList);

		List<DepartmentDto> departmentDtoList = hospitalInfoService
				.getAllDepartsByHospitalId(hospitalDtoList.get(0).getHospitalId());
		model.addAttribute("departmentDtoList", departmentDtoList);

		ModelAndView mav = new ModelAndView("doctorInfo");

		return mav;

	}

	@PostMapping("/addDoctorInfo")
	public String addDoctorInfo(
			@RequestParam("doctorName") String doctorName,
			@RequestParam("hospitalId") int hospitalId,
			@RequestParam("departmentId") int departmentId,
			Model model) {
		
		doctorInfoService.addDoctorInfo(doctorName, hospitalId, departmentId);
		
		return "login"; 
	}

	@GetMapping("/getDepartmentsByHospital/{hospitalId}")
	@ResponseBody
	public List<DepartmentDto> getDepartmentsByHospital(@PathVariable("hospitalId") int hospitalId) {

		System.out.println(hospitalId);

		// 根据 hospitalId 获取对应的部门列表
		List<DepartmentDto> departmentDtoList = hospitalInfoService
				.getAllDepartsByHospitalId(hospitalId);

		return departmentDtoList;
	}

	@GetMapping("/generateUserInfo")
	public ModelAndView generateDoctorId(Model model) {
		String doctorId = userLoginService.generateDoctorId(); // 实际生成ID的逻辑
		System.out.println("Generated Doctor ID: " + doctorId);
		if (doctorId == null || doctorId.isEmpty()) {
			doctorId = "DEFAULT_ID"; // 默认值，调试时用
		}
		// 返回生成的doctorId作为响应
		ModelAndView mav = new ModelAndView("doctorIdGenerator");

		model.addAttribute("doctorId", doctorId);
		return mav;
	}
}