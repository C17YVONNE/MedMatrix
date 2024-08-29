package com.emrsys.medmatrix.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "hospital")
@Data
public class HospitalEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private int hospitalId;

    @Column(name = "hospital_name", nullable = false, length = 50)
    private String hospitalName;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "number", nullable = false, length = 255)
    private String number;

    @OneToMany(mappedBy = "hospitalEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartmentEntity> departmentEntity;
}
