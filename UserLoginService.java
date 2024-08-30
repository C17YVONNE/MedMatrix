package com.emrsys.medmatrix.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emrsys.medmatrix.entity.UserLoginEntity;
import com.emrsys.medmatrix.object.UserLoginDto;
import com.emrsys.medmatrix.repository.UserLoginRepository;
import com.emrsys.medmatrix.util.MedConst;
import com.emrsys.medmatrix.util.MsgContents;
import com.emrsys.medmatrix.util.PwdHashing;
import com.emrsys.medmatrix.util.Status;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserLoginService {

	@Autowired
	UserLoginRepository userLoginRepository;

	public UserLoginService(UserLoginRepository userLoginRepository) {
		this.userLoginRepository = userLoginRepository;
	}

	/**
	 * ログインチェック
	 * @param user
	 * @return
	 */
	public String loginProcess(UserLoginDto user) {
		Optional<UserLoginEntity> optionalUser = userLoginRepository.findByDoctorId(user.getDoctorId());
		if (!optionalUser.isPresent()) {
			return MsgContents.CHECKFALSE;
		}
		// 获取实际的 UserLoginEntity 对象
		UserLoginEntity ud = optionalUser.get();

		//入力した医師IDとパスワードとDBのデータが一致するかどうか
		if (!PwdHashing.matches(user.getPassword(), ud.getPassword())) {
			return MsgContents.CHECKFALSE;
		} else {
			//ログインさせ
			return MsgContents.CHECKTRUE;
		}
	}

	/**
	 * Generate a unique doctor ID.
	 * 
	 * @return Generated doctor ID
	 */
	public String generateDoctorId() {
		Random random = new Random();
		String doctorId;
		boolean exists;

		do {
			int number = random.nextInt(999999 - 1000) + 1000; // Generate a random number between 1000 and 999999
			doctorId = "D" + number;
			exists = userLoginRepository.existsByDoctorId(doctorId); // Check if the ID already exists
		} while (exists); // If it exists, generate a new one

		return doctorId;
	}

	/**
	 * Create a new user with a generated doctor ID.
	 * 
	 * @param user The UserLoginDto containing user information
	 * @return Success or failure message
	 */
	public String createUser(UserLoginDto user) {
		// Generate a unique doctorId
		String doctorId = generateDoctorId();
		if (doctorId == null || doctorId.isEmpty()) {
			throw new RuntimeException("Failed to generate a valid Doctor ID");
		} else {
			// Convert DTO to Entity and set additional fields
			UserLoginEntity entity = new UserLoginEntity();
			entity.setDoctorId(doctorId);
			entity.setPassword(PwdHashing.pwdEnCode(doctorId));
			entity.setUrole("user");
			entity.setStatus(Status.USERNORMAL);
			entity.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));

			// Save the new entity to the database
			userLoginRepository.save(entity);
			System.out.println("Generated doctor ID: " + doctorId);

			// Return success constant
			return MedConst.CREATE_USER_SUCCESS;
		}
	}

	/**
	 * 医師ID存在するチェック
	 * @param user
	 * @return
	 */
	public String checkIfDoctorIdExists(UserLoginDto user) {
		Optional<UserLoginEntity> optionalUser = userLoginRepository.findByDoctorId(user.getDoctorId());

		if (optionalUser.isPresent()) {
			return MsgContents.DOCYES;
		} else {
			return MsgContents.DOCNO;
		}
	}

	/**
	 * get DBのPwd
	 * @param doctorId
	 * @return
	 */
	public String getPwd(String doctorId) {

		return userLoginRepository.findByDoctorId(doctorId)
				.map(UserLoginEntity::getPassword)
				.orElse(null);
	}

	/**
	 * get DBのRole
	 * @param doctorId
	 * @return
	 */
	public String getUrole(String doctorId) {

		return userLoginRepository.findByDoctorId(doctorId)
				.map(UserLoginEntity::getUrole)
				.orElse(null);
	}

	/**
	 * get DBのStatus
	 * @param doctorId
	 * @return
	 */
	public int getStatus(String doctorId) {

		return userLoginRepository.findByDoctorId(doctorId)
				.map(UserLoginEntity::getStatus)
				.orElseThrow(() -> new RuntimeException("User not found with doctorId: " + doctorId));
	}

}
