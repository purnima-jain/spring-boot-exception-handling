package com.purnima.jain.json;

import com.purnima.jain.domain.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerGetResponseDto {	
	
	private Integer customerId;
	private String name;
	private Integer age;
	
	public CustomerGetResponseDto(Customer customer) {
		this.customerId = customer.getCustomerId();
		this.name = String.format("%s %s", customer.getFirstName(), customer.getLastName());
		this.age = customer.getAge();
	}

}