package com.emrsys.medmatrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.emrsys.medmatrix.entity.UserLoginEntity;
import com.emrsys.medmatrix.object.UserLoginDto;
import com.emrsys.medmatrix.service.UserLoginService;
import com.emrsys.medmatrix.util.MsgContents;
import com.emrsys.medmatrix.util.Url;
import com.emrsys.medmatrix.util.UserInfoSession;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserLoginController {
	@Autowired
	UserLoginService userLoginService;

	/**
	 * ログイン画面
	 * @return
	 */
	@GetMapping(Url.LOGIN)
	public ModelAndView showLoginForm() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	/**
	 * ユーザーログイン
	 * @param user
	 * @return
	 */
	@PostMapping(Url.LOGIN)
	public String Login(UserLoginDto userLoginDto, HttpSession httpSession, Model model) {
		String rawPassword = userLoginDto.getPassword();
		int doctorId = userLoginDto.getDoctorId();
		
		// 获取数据库中存储的加密密码
		UserLoginEntity userLogin = userLoginService.getUserLoginByDoctorId(doctorId);
		String storedEnncodedPassword = userLogin.getPassword();
	

		// 验证密码是否匹配
		if (userLoginService.checkPassword(rawPassword, storedEnncodedPassword)) {
			// 如果匹配，设置登录会话信息
			UserInfoSession userInfoSession = userLoginService.getLoginSession(doctorId);
			httpSession.setAttribute("session", userInfoSession);// 将Session对象放入HttpSession中
			return "redirect:/home";
		} else {
			model.addAttribute("error", MsgContents.LOGINFAILUER);
			return "login";
		}
	}
}
