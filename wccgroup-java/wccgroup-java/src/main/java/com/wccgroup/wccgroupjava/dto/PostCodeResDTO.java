package com.wccgroup.wccgroupjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCodeResDTO {
	private String postCode1;
	private String postCode2;
	private double latitude1;
	private double longtitude1;
	private double latitude2;
	private double longtitude2;
	private String distance;
	
}
