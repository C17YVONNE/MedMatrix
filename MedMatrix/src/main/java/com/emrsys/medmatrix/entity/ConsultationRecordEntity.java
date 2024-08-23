package com.emrsys.medmatrix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ConsultationRecordEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long consultationId;
	
	/*@ManyToOne
	@JoinColumn(name = "hospital_id", nullable = false)
	
	*/
	
}
