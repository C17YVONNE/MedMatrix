package com.emrsys.medmatrix.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emrsys.medmatrix.entity.UserLoginEntity;
import com.emrsys.medmatrix.object.UserLoginDto;
import com.emrsys.medmatrix.repository.UserLoginRepository;
import com.emrsys.medmatrix.util.MsgContents;
import com.emrsys.medmatrix.util.PwdHashing;
import com.emrsys.medmatrix.util.Status;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserLoginService {
	
	@Autowired
	UserLoginRepository userLoginRepository;
	
	/**
	 * ログインチェック
	 * @param user
	 * @return
	 */
	public String loginProcess(UserLoginDto user) {
		UserLoginEntity ud = userLoginRepository.findByDoctorId(user.getDoctorId());
		if (ud == null) {
			return MsgContents.CHECKFALSE;
		}
		String pwd = PwdHashing.pwdEnCode(user.getPassword());
		//入力した医師IDとパスワードとDBのデータが一致するかどうか
		if(!ud.getDoctorId().equals(user.getDoctorId()) || !ud.getPassword().equals(pwd)){
			return MsgContents.CHECKFALSE;
		} else {
			//ログインさせ
			return MsgContents.CHECKTRUE;
		}
	}
	
	/**
	 * ユーザ作成
	 * @param user
	 * @return 0 作成成功 １作成失敗
	 */
	public int createUser(UserLoginDto user) {

		UserLoginEntity ue = userLoginRepository.findByDoctorId(user.getDoctorId());
		UserLoginEntity entity = new UserLoginEntity();
		//新規医師ID
		if (ue == null) {
			entity.setDoctorId(user.getDoctorId());
			entity.setPassword(user.getPassword());
			entity.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
			entity.setUrole("user");
			entity.setStatus(Status.USERNORMAL);
			userLoginRepository.save(entity);
			return 0;

		} else {
			//入力したメールアドレスがDBにすでに存在(登録)
			//入力したメールアドレスが登録されいない(ログイン)
			return 1;
		}

	}
	

	/**
	 * 医師ID存在するチェック
	 * @param user
	 * @return
	 */
	public String doctorIdCheck(UserLoginDto user) {
		UserLoginEntity ue = userLoginRepository.findByDoctorId(user.getDoctorId());

		if (ue == null) {

			return MsgContents.DOCNO;
		} else {
			return MsgContents.DOCYES;
		}
	}
	/**
	 * get DBのPwd
	 * @param doctorId
	 * @return
	 */
	public String getPwd(String doctorId) {

		return userLoginRepository.findByDoctorId(doctorId).getPassword();
	}
	
	/**
	 * get DBのRole
	 * @param doctorId
	 * @return
	 */
	public String getUrole(String doctorId) {

		return userLoginRepository.findByDoctorId(doctorId).getUrole();
	}
	
	/**
	 * get DBのStatus
	 * @param doctorId
	 * @return
	 */
	public int getStatus(String doctorId) {

		return userLoginRepository.findByDoctorId(doctorId).getStatus();
	}

}
