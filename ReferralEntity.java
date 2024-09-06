package com.emrsys.medmatrix.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "referrals")
@Data
public class ReferralEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	
    @Column(name = "patient_id", length = 10, nullable = false)
    private String patientId;

    @Column(name = "doctor_id", length = 7, nullable = false)
    private int doctorId;

    @Column(name = "hospital_id", nullable = false)
    private int hospitalId;

    @Column(name = "injury_name", nullable = false, columnDefinition = "text")
    private String injuryName;

    @Column(name = "intro_purpose", nullable = false, columnDefinition = "text")
    private String introPurpose;

    @Column(name = "medical_history", columnDefinition = "text")
    private String medicalHistory;

    @Column(name = "family_history", columnDefinition = "text")
    private String familyHistory;

    @Column(name = "consultation", nullable = false, columnDefinition = "text")
    private String consultation;

    @Column(name = "treatment_process", columnDefinition = "text")
    private String treatmentProcess;

    @Column(name = "current_prescription", columnDefinition = "text")
    private String currentPrescription;

    @Column(name = "physical_findings", columnDefinition = "text")
    private String physicalFindings;

    @Column(name = "lifestyle_habits", columnDefinition = "text")
    private String lifestyleHabits;

    @Column(name = "del_flag", nullable = false, columnDefinition = "varchar(1) default '0'")
    private String delFlag = "0";  // 默认值为 '0'

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;  // 插入时自动设置当前时间

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;  // 插入或更新时自动更新为当前时间

    // 在实体类中定义一个默认构造函数
    public ReferralEntity() {
        this.createdAt = LocalDateTime.now();  // 设置当前时间
        this.updatedAt = LocalDateTime.now();  // 设置当前时间
    }
}
