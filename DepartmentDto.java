package com.emrsys.medmatrix.object;

import lombok.Data;

@Data
public class DepartmentDto {
	private int departmentId;
    private String departmentName;
    private int hospitalId;
}
