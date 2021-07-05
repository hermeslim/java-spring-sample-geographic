package com.wccgroup.wccgroupjava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Locations {

	public Locations(String locationFrom, String locationTo, String postcode) {
		this.locationFrom = locationFrom;
		this.locationTo = locationTo;
		this.postcode = postcode;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "locationFrom")
	private String locationFrom;
	
	@Column(name = "locationTo")
	private String locationTo;
	
	@Column(name = "postcode")
	private String postcode;
}
