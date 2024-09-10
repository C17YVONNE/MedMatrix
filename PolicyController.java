package com.emrsys.medmatrix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class PolicyController extends BasePageController {

	@GetMapping("/privacy-policy")
	public ModelAndView showPrivacyPolicy(HttpSession session) {
		return createMav("privacy-policy", session);
	}

	@GetMapping("/terms-of-use")
	public ModelAndView showTermsOfUse(HttpSession session) {
		return createMav("terms-of-use", session);
	}

	@GetMapping("/help-center")

	public ModelAndView showHelpCenter(HttpSession session) {
		return createMav("help-center", session);
	}
}
