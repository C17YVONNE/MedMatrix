package com.emrsys.medmatrix.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "doctor")
@Data
public class DoctorInfoEntity {

	    @Id
	    @Column(name = "doctor_id", length = 7, nullable = false)
	    private String doctorId;

	    @Column(name = "name", length = 50, nullable = false)
	    private String name;

	    @Column(name = "hospital_id", nullable = false)
	    private Integer hospitalId;

	    @Column(name = "department_id", nullable = false)
	    private Integer departmentId;

	    @Column(name = "del_flag", length = 1, nullable = false)
	    private String delFlag = "0";

	    @Column(name = "created_at", nullable = false, updatable = false)
	    private LocalDateTime createdAt = LocalDateTime.now();

	    @Column(name = "updated_at", nullable = false)
	    private LocalDateTime updatedAt = LocalDateTime.now();
}
