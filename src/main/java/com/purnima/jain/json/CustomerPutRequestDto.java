package com.purnima.jain.json;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPutRequestDto {
	
	@NotNull(message = "Customer First Name cannot be null")
	@NotBlank(message = "Customer First Name cannot be blank")
	private String firstName;
	
	@NotNull(message = "Customer Last Name cannot be null")
	@NotBlank(message = "Customer Last Name cannot be blank")
	private String lastName;
	
	@NotNull(message = "Customer Age cannot be null")
	private Integer age;
	
}
