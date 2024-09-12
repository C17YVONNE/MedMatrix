package com.emrsys.medmatrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emrsys.medmatrix.object.HospitalDto;
import com.emrsys.medmatrix.service.HospitalInfoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HospitalInfoController extends BasePageController {

	@Autowired
	private HospitalInfoService hospitalInfoService;

	@GetMapping("/hospitalDetails")
	@ResponseBody
	public HospitalDto getHospitalDetails(@RequestParam("hospitalId") int hospitalId) {
		HospitalDto hospitaldto = hospitalInfoService.findHospitalInfoByHospitalId(hospitalId);
		return hospitaldto;
	}

	@GetMapping("/addHospitalInfo")
	public ModelAndView ShowAddHospitalInfoForm(HttpSession session, Model model) {
		return createMav("hospitalInfo", session);
	}

	@PostMapping("/addHospitalInfo")
	public ModelAndView AddHospitalInfo(
			@RequestParam("hospitalName") String hospitalName,
			@RequestParam("postcode") String postcode,
			@RequestParam("address") String address,
			@RequestParam("number") String number,
			HttpSession session,
			Model model) {

		hospitalInfoService.addHospitalInfo(hospitalName, postcode, address, number);
		model.addAttribute("infoMessage", "医療機関情報が正常に保存されました。");
		return createMav("login", session);
	}
}
