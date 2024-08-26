package com.emrsys.medmatrix.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "patients")
public class PatientEntity {
	@Id
	@Column(name = "patient_id")
	private String patientId;
	@Column(name = "name")
	private String name;
	@Column(name = "gender")
	private String gender;
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	@Column(name = "address")
	private String address;
	@Column(name = "contact_number")
	private String contactNum;
	@Column(name = "emergency_contact")
	private String emerNum;
	@Column(name = "del_flag", nullable = false)
	private String delFlag = "0";
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createTime;
	@Column(name = "updated_at")
	private LocalDateTime updateTime;

	@PrePersist
	protected void onCreate() {
	    this.createTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
	    this.updateTime = LocalDateTime.now();
	}
}
