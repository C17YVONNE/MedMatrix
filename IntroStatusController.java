package com.emrsys.medmatrix.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.emrsys.medmatrix.entity.IntroStatusEntity;
import com.emrsys.medmatrix.repository.IntroStatusRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class IntroStatusController extends BasePageController{

	private final IntroStatusRepository introStatusRepository;
	
	public IntroStatusController(IntroStatusRepository introStatusRepository) {
		this.introStatusRepository = introStatusRepository;
	}
	
	@GetMapping("/showIntroStatus")
	public ModelAndView showIntroStauts(HttpSession session,Model model) {
		List<IntroStatusEntity> introStatusList = introStatusRepository.findAll();
		model.addAttribute("introStatusList",introStatusList);
		return createMav("introStatus",session);
	}
}
