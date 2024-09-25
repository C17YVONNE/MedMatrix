package com.emrsys.medmatrix.object;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MedicineOjt {
	private Long medicineId;
	private String name;
	private String description;
	private BigDecimal price;

	// This will be used for the file upload from the form
	private MultipartFile picLinkFile;

	// This will store the file path in the database
	private String picLink;

}
