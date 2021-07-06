package com.wccgroup.wccgroupjava.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCodeResDTO {
	@NotNull
	@Size(min = 1, max = 255, message = "must be between 1 to 255 characters")
	private String postCode1;
	
	@NotNull
	@Size(min = 1, max = 255, message = "must be between 1 to 255 characters")
	private String postCode2;
	
	private double latitude1;
	private double longtitude1;
	private double latitude2;
	private double longtitude2;
	private String distance;
	
}
