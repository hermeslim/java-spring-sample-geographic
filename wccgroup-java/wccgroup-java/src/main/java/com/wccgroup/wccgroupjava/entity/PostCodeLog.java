package com.wccgroup.wccgroupjava.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "POSTCODELOG")
public class PostCodeLog {
	
	@Id
	@GeneratedValue
	private long id;

	@Column(name = "postcode1")
	@Size(min = 1, max = 255, message = "must between 1 to 255 characters")
	private String postCode1;
	
	@Column(name = "postcode2")
	@Size(min = 1, max = 255, message = "must between 1 to 255 characters")
	private String postCode2;
	
	 @CreationTimestamp
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "CREATEDDATE", nullable = false)
	 private Date createdDate;

}
