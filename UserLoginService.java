package com.emrsys.medmatrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emrsys.medmatrix.entity.DoctorInfoEntity;
import com.emrsys.medmatrix.entity.HospitalEntity;
import com.emrsys.medmatrix.entity.UserLoginEntity;
import com.emrsys.medmatrix.repository.DoctorInfoRepository;
import com.emrsys.medmatrix.repository.HospitalRepository;
import com.emrsys.medmatrix.repository.UserLoginRepository;
import com.emrsys.medmatrix.util.Session;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserLoginService {
	@Autowired
    private UserLoginRepository userLoginRepository;
	
	@Autowired
	private DoctorInfoRepository doctorInfoRepository;
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserLoginService() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	public String encryptPassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
	
	public boolean checkPassword(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
	
	public UserLoginEntity getUserLoginByDoctorId(int doctorId) {
        return userLoginRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctorId"));
    }
	
	public Session getLoginSession(int doctorId) {
		// 获取登录信息
        UserLoginEntity userLogin = userLoginRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctorId"));
		// 获取医生信息
		DoctorInfoEntity doctorInfo = doctorInfoRepository.findById(doctorId)	.orElseThrow(() -> new IllegalArgumentException("Invalid doctorId"));
		
		// 获取医院信息
		HospitalEntity hospital = hospitalRepository.findById(doctorInfo.getHospitalId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid hospitalId"));
		
		Session session = new Session();
        session.setDoctorId(doctorInfo.getDoctorId());
        session.setName(doctorInfo.getName());
        session.setUrole(userLogin.getUrole()); 
        session.setLocked(userLogin.isLocked());
        session.setHospitalName(hospital.getHospitalName());

        return session;
	}
}


