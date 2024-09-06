package com.emrsys.medmatrix.object;

import java.sql.Date;

import lombok.Data;

@Data
public class ClinicalFindingsDto {
	private String patientId;
	private int doctorId;
	private Date consultationDate;
	private String subjective;
	private String objective;
	private String assessment;
	private String plan;
}
