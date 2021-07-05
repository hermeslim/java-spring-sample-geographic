package com.wccgroup.wccgroupjava.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCodeReqDTO {
	private List<String> postCodes;
	private String postCode1;
	private String postCode2;
	private String postCode;
	private double latitude;
	private double longtitude;
	
}
