package com.purnima.jain.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	private Integer customerId;
	private String firstName;
	private String lastName;
	private Integer age;

}

