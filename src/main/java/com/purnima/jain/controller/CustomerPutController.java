package com.purnima.jain.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.purnima.jain.domain.Customer;
import com.purnima.jain.exception.custom.ResourceNotFoundException;
import com.purnima.jain.json.CustomerGetResponseDto;
import com.purnima.jain.json.CustomerPutRequestDto;
import com.purnima.jain.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
public class CustomerPutController {
	
	@Autowired
	private CustomerService customerService;
	
	// Sample Input (Valid: 200-Ok): http://localhost:8080/customers/1408     {     "firstName": "John",     "lastName": "Doe",     "age": 138 }
	// Sample Input (Valid: 400-Bad Request): http://localhost:8080/customers/14082     {     "firstName": "John",     "lastName": "Doe",     "age": 138 }
	// Throws ConstraintViolationException with the message "must be less than or equal to 9999"
	// Sample Input (Valid: 400-Bad Request): http://localhost:8080/customers/14     {     "firstName": "John",     "lastName": "Doe",     "age": 138 }
	// Throws ConstraintViolationException with the message "must be greater than or equal to 1000" 
	@PutMapping(value = "/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerGetResponseDto> updateCustomer(@PathVariable("customerId") @Min(1000) @Max(9999) Integer customerId, @Valid @RequestBody CustomerPutRequestDto customerPutRequestDto) {
		log.info("Input Customer Id :: {}", customerId);
		
		Optional<Customer> customerOptional = customerService.getCustomerById(customerId);
		
		Customer customer = customerOptional.orElseThrow(() -> new ResourceNotFoundException(Customer.class, "customerId", customerId.toString()));
		customer.setFirstName(customerPutRequestDto.getFirstName());
		customer.setLastName(customerPutRequestDto.getLastName());
		customer.setAge(customerPutRequestDto.getAge());
		
		Customer updatedCustomer = customerService.updateCustomer(customer);
		
		return ResponseEntity.ok().body(new CustomerGetResponseDto(updatedCustomer));	
	}
}
