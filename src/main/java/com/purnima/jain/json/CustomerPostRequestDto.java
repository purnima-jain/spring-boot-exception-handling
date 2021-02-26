package com.purnima.jain.json;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.purnima.jain.domain.Customer;
import com.purnima.jain.util.ResourceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPostRequestDto {
	
	@NotNull(message = "Customer First Name cannot be null")
	@NotBlank(message = "Customer First Name cannot be blank")
	private String firstName;
	
	@NotNull(message = "Customer Last Name cannot be null")
	@NotBlank(message = "Customer Last Name cannot be blank")
	private String lastName;
	
	@NotNull(message = "Customer Age cannot be null")
	private Integer age;
	
	public Customer toCustomer() {
		Customer customer = new Customer();
		
		customer.setCustomerId(ResourceIdGenerator.generateResourceId());
		customer.setFirstName(this.firstName);
		customer.setLastName(this.lastName);
		customer.setAge(this.age);
		
		return customer;
	}

}