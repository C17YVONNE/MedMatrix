package com.emrsys.medmatrix.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "medicine")
public class MedicineEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medicine_id")
	private Long medicineId;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "picLink", length = 100)
	private String picLink;

	@Column(name = "description")
	private String description;

	@Column(name = "price", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;
}
