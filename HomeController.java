package com.emrsys.medmatrix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController extends BasePageController {

	@GetMapping("/home")
	public ModelAndView home(HttpSession session) {
		return createMav("home", session);
	}
}
