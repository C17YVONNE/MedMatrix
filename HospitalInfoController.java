package com.emrsys.medmatrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.emrsys.medmatrix.service.HospitalInfoService;

@Controller
public class HospitalInfoController {

	@Autowired
	private HospitalInfoService hospitalInfoService;

	@GetMapping("/addHospitalInfo")
	public ModelAndView ShowAddHospitalInfoForm(Model model) {
		ModelAndView mav = new ModelAndView("hospitalInfo");
		return mav;
	}

	@PostMapping("/addHospitalInfo")
	public String AddHospitalInfo(
			@RequestParam("hospitalName") String hospitalName,
			@RequestParam("postcode") String postcode, 
			@RequestParam("address") String address,
			@RequestParam("number") String number, 
			Model model) {
		
		hospitalInfoService.addHospitalInfo(hospitalName, postcode, address, number);
		model.addAttribute("infoMessage", "医療機関情報が正常に保存されました。");
		return "login";
	}
}
