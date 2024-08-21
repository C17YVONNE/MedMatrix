package com.emrsys.medmatrix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.emrsys.medmatrix.entity.UserLoginEntity;
import com.emrsys.medmatrix.repository.UserLoginRepository;
import com.emrsys.medmatrix.util.PwdHashing;

public class PswEncryptionService {
	 @Autowired
	    UserLoginRepository userLoginRepository;

	    public void encryptAndSavePasswords() {
	        List<UserLoginEntity> users = userLoginRepository.findAll();

	        for (UserLoginEntity user : users) {
	            // 对现有的密码进行MD5加密
	            String encryptedPassword = PwdHashing.pwdEnCode(user.getPassword());
	            user.setPassword(encryptedPassword);
	            // 保存更新后的实体
	            userLoginRepository.save(user);
	        }
	    }
}
