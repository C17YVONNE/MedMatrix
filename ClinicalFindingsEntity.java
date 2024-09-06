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
@Table(name = "clinical_findings")
@Data
public class ClinicalFindingsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String patientId;
	private int doctorId;
	private Date consultationDate;
	private String subjective;
	private String objective;
	private String assessment;
	private String plan;

	@Column(name = "del_flag", nullable = false, columnDefinition = "varchar(1) default '0'")
	private String delFlag = "0"; // 默认值为 '0'

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt; // 插入时自动设置当前时间

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt; // 插入或更新时自动更新为当前时间

	// 在实体类中定义一个默认构造函数
	public ClinicalFindingsEntity() {
		this.createdAt = LocalDateTime.now(); // 设置当前时间
		this.updatedAt = LocalDateTime.now(); // 设置当前时间
	}
}

