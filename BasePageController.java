package com.emrsys.medmatrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.emrsys.medmatrix.entity.DoctorInfoEntity;
import com.emrsys.medmatrix.entity.HospitalEntity;
import com.emrsys.medmatrix.entity.UserLoginEntity;
import com.emrsys.medmatrix.repository.DoctorInfoRepository;
import com.emrsys.medmatrix.repository.HospitalRepository;
import com.emrsys.medmatrix.repository.UserLoginRepository;
import com.emrsys.medmatrix.util.UserInfoSession;

public class BasePageController {
	
	@Autowired
    private UserLoginRepository userLoginRepository;
	
	@Autowired
	private DoctorInfoRepository doctorInfoRepository;
	
	@Autowired
	private HospitalRepository hospitalRepository;

	public ModelAndView createMav(String page) {
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Frame");
		mav.addObject("Page", "pages/" + page);
		return mav;
		
	}

	
	public UserInfoSession getLoginSession(int doctorId) {
		// 获取登录信息
        UserLoginEntity userLogin = userLoginRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctorId"));
		// 获取医生信息
		DoctorInfoEntity doctorInfo = doctorInfoRepository.findById(doctorId)	.orElseThrow(() -> new IllegalArgumentException("Invalid doctorId"));
		
		// 获取医院信息
		HospitalEntity hospital = hospitalRepository.findById(doctorInfo.getHospitalId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid hospitalId"));
		
		UserInfoSession userInfoSession = new UserInfoSession();
        userInfoSession.setDoctorId(doctorInfo.getDoctorId());
        userInfoSession.setName(doctorInfo.getName());
        userInfoSession.setUrole(userLogin.getUrole()); 
        userInfoSession.setLocked(userLogin.isLocked());
        userInfoSession.setHospitalName(hospital.getHospitalName());

        return userInfoSession;
	}
}
