package com.emrsys.medmatrix.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emrsys.medmatrix.object.IntroStatusDto;
import com.emrsys.medmatrix.object.PatientDto;
import com.emrsys.medmatrix.service.IntroStatusService;
import com.emrsys.medmatrix.service.PatientInfoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController extends BasePageController {

	@Autowired
	private IntroStatusService introStatusService;

	@Autowired
	private PatientInfoService patientInfoService;

	@GetMapping("/home")
	public ModelAndView searchDocuStatus(@RequestParam(value = "status") String status,
			@RequestParam(value = "patientId", required = false) String patientId,
			@RequestParam(value = "intro_from", required = false) String introFrom,
			@RequestParam(value = "startdate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "enddate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(value = "keyword", required = false) String keyword,
			HttpSession session,
			Model model) {
		// 调用Service层进行数据库查询
		List<IntroStatusDto> introStatusList = introStatusService.searchIntroStatus(patientId, status, introFrom,
				startDate, endDate, keyword);

		// 将查询结果添加到model中传递给前端页面
		model.addAttribute("introStatusList", introStatusList);

		return createMav("home", session);
	}
	
	@GetMapping("/getPatientName")
	@ResponseBody
	public String getPatientName(@RequestParam("patientId") String patientId) {
	    PatientDto patient = patientInfoService.findPatientInfoByPatientId(patientId);
	    if (patient != null) {
	        return patient.getName();
	    } else {
	        return "不明な患者"; // 返回一个默认的患者名
	    }
	}
}
