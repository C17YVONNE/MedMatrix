package com.emrsys.medmatrix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.emrsys.medmatrix.util.UserInfoSession;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home(HttpSession httpSession, Model model) {
		UserInfoSession userInfoSession = (UserInfoSession) httpSession.getAttribute("session");

		// 检查Session对象是否存在，防止直接访问 /home 页面而没有登录
		if (userInfoSession == null) {
			// 如果没有Session对象，重定向到登录页面
			return "redirect:/login";
		}
		model.addAttribute("userInfoSession", userInfoSession); 
		System.out.println(userInfoSession);
		System.out.println("/n");
		return "home";
	}
}
