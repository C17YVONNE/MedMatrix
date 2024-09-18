package com.emrsys.medmatrix.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
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

	@Column(name = "patient_id")
	private String patientId;

	@Column(name = "document_id")
	private int documentId;

	@Column(name = "intro_date")
	private Date introDate;

	@Column(name = "intro_from")
	private String introFrom;

	@Column(name = "intro_to")
	private String introTo;

	@Column(name = "status")
	private String status;

	@Column(name = "del_flag")
	private String delFlag;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public IntroStatusEntity() {
		this.createdAt = LocalDateTime.now(); // 设置当前时间
		this.updatedAt = LocalDateTime.now(); // 设置当前时间
	}
}
