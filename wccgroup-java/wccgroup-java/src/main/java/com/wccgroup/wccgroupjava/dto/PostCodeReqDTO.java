package com.wccgroup.wccgroupjava.dto;

import java.util.List;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCodeReqDTO {
	private List<String> postCodes;
	
	@Size(min = 1, max = 255, message = "must be between 1 to 255 characters")
	private String postCode1;
	
	@Size(min = 1, max = 255, message = "must be between 1 to 255 characters")
	private String postCode2;
	
	private String postCode;
	private double latitude;
	private double longtitude;
	
}
