package com.emrsys.medmatrix.object;

import java.sql.Date;

import lombok.Data;

@Data
public class IntroStatusDto {
	private String patientId;
	private int documentId;
	private Date introDate;
	private String introFrom;
	private String introTo;
	private String status;
	
	private String patientName;
}
