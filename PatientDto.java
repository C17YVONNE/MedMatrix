package com.emrsys.medmatrix.object;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDto {
	
	private String patientId;
	private String name;
	private String gender;
	private Date dateOfBirth;
	private String address;
	private String contactNum;
	private String emerNum;
	private String delFlag;
	private LocalDateTime createdAt;
}
