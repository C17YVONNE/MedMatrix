package com.emrsys.medmatrix.object;

import java.security.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
	private String doctorId;
	private String password;
	private String urole;
	private Timestamp lastLogin;
	private int status;
	private Timestamp createTime;
	private Timestamp updateTime;
}
