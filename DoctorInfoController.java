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

import jakarta.servlet.http.HttpSession;

@Controller
public class DoctorInfoController extends BasePageController{

	@Autowired
	private HospitalInfoService hospitalInfoService;
	
	@Autowired
	DoctorInfoService doctorInfoService;

	@GetMapping("/addDoctorInfo")
	public ModelAndView ShowAddDoctorInfoForm(HttpSession session, Model model) {

		List<HospitalDto> hospitalDtoList = hospitalInfoService.getAllHospitals();

		model.addAttribute("hospitalDtoList", hospitalDtoList);

		List<DepartmentDto> departmentDtoList = hospitalInfoService
				.getAllDepartsByHospitalId(hospitalDtoList.get(0).getHospitalId());
		model.addAttribute("departmentDtoList", departmentDtoList);

		return createMav("doctorInfo",session);

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
}