package com.purnima.jain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CUSTOMER_INFO")
@Data
public class CustomerEntity {

	@Id
	@Column(name = "CUSTOMER_ID")
	private Integer customerId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "AGE")
	private Integer age;

}