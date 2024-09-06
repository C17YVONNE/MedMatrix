package com.emrsys.medmatrix.object;

import lombok.Data;

@Data
public class ReferralDto {

	private String patientId;
	private int doctorId;
	private int hospitalId;
	private String injuryName;
	private String introPurpose;
	private String medicalHistory;
	private String familyHistory;
	private String consultation;
	private String treatmentProcess;
	private String currentPrescription;
	private String physicalFindings;
	private String lifestyleHabits;
}
