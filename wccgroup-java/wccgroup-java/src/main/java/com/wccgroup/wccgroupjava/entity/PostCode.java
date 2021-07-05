package com.wccgroup.wccgroupjava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "POSTCODE")
public class PostCode {
	
	@Id
	@GeneratedValue
	private long id;

	@Column(name = "postcode")
	private String postCode;
	
	@Column(name = "latitude")
	private double latitude;
	
	@Column(name = "longtitude")
	private double longtitude;
}
