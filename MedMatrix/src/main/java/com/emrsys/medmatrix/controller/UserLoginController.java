package com.emrsys.medmatrix.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.emrsys.medmatrix.object.UserLoginDto;
import com.emrsys.medmatrix.service.UserLoginService;
import com.emrsys.medmatrix.util.MsgContents;
import com.emrsys.medmatrix.util.Session;
import com.emrsys.medmatrix.util.Url;

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
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	/**
	 * ユーザーログイン
	 * @param user
	 * @return
	 */
	@PostMapping(Url.LOGIN)
	public String userLogin(UserLoginDto user, Map<String, Object> map, HttpSession session) {

			int status = userLoginService.getStatus(user.getDoctorId());
			Session sessionDto = new Session();
			sessionDto.setDoctorId(user.getDoctorId());
			sessionDto.setUrole(userLoginService.getUrole(user.getDoctorId()));
			sessionDto.setStatus(status);
			
			if(status == 1) {
				String idPsCheck = userLoginService.loginProcess(user);
				//医師IDとパスワードがDBのと一致しない場合
				if (idPsCheck.equals(MsgContents.CHECKFALSE)) {
					//エラーメッセージ提示
					map.put("idpscheck", MsgContents.LOGINFAILUER);
					return "login";
				}else {
					//ログイン成功 ホームページへ

					session.setAttribute("userLogin", sessionDto);

					return "redirect:/home";
				}
			}else {
				map.put("statusCheck", MsgContents.STATUSCHECK);
				return "login";
			}
		}
	

	/**
	 * ログアウト 
	 * 
	 */
	@RequestMapping(Url.LOGINOUT)
	public String loginout(HttpSession session) {

		session.removeAttribute("userLogin");

		return "redirect:/home";

	}
}
