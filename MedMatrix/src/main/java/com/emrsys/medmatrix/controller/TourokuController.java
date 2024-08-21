package com.emrsys.medmatrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.emrsys.medmatrix.object.UserLoginDto;
import com.emrsys.medmatrix.service.UserLoginService;
import com.emrsys.medmatrix.util.MsgContents;
import com.emrsys.medmatrix.util.PwdHashing;
import com.emrsys.medmatrix.util.Url;

public class TourokuController {
	@Autowired 
	UserLoginService userLoginService;
	
	
	/**
	 * 登録画面
	 * @return
	 */
	@GetMapping(Url.TOUROKU)
	public ModelAndView toroku() {
		ModelAndView mv = new ModelAndView("touroku");
		return mv;
	}
	
	/**
	 * ユーザー登録
	 * @param user
	 * @return
	 */
	@PostMapping(Url.TOUROKU)
	public String createUser(UserLoginDto user, Model model) {

		//パスワード非表示設定
		String pwd = PwdHashing.pwdEnCode(user.getPassword());

		UserLoginDto use = new UserLoginDto();
		use.setDoctorId(user.getDoctorId());
		use.setPassword(pwd);
		//ユーザ作成
		int docCheck = userLoginService.createUser(use);

		//登録失敗
		if (docCheck == 1) {
			model.addAttribute("msg", MsgContents.REGISTRATEFAILUER);
			//登録画面に遷移
			return "touroku";
		} else {
			//ログイン画面に遷移
			return "login";
		}
	}
}
