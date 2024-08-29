package com.emrsys.medmatrix.object;

import java.util.List;

import lombok.Data;

@Data
public class HospitalDto {
	private int hospitalId;
    private String hospitalName;
    private String postcode;
    private String address;
    private String number;
    private List<DepartmentDto> departments;
}
