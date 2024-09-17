package com.emrsys.medmatrix.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "intro_status")
@Data
public class IntroStatusEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String patientId;
	private int documentId;
	private Date introDate;
	private String introFrom;
	private String introTo;
	private String status;
	private String delFlag;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public IntroStatusEntity() {
		this.createdAt = LocalDateTime.now(); // 设置当前时间
		this.updatedAt = LocalDateTime.now(); // 设置当前时间
	}
}
