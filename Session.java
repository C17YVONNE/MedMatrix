package com.emrsys.medmatrix.util;

import lombok.Data;

@Data
public class Session {
	
	private int doctorId;
	private String name;
	private String urole;
	private boolean locked;
	private String hospitalName;
}
