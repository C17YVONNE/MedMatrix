package com.emrsys.medmatrix.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "login")
public class UserLoginEntity {
	@Id
	@Column(name = "doctor_id")
	private String doctorId;
	@Column(name = "password")
	private String password;
	@Column(name = "urole")
	private String urole;
	@Column(name = "last_login")
	private Timestamp lastLogin;
	@Column(name = "status")
	private int status;
	@Column(name = "created_at")
	private Timestamp createTime;
	@Column(name = "updated_at")
	private Timestamp updateTime;
}
