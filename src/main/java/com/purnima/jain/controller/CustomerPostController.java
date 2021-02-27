package com.purnima.jain.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.purnima.jain.domain.Customer;
import com.purnima.jain.json.CustomerPostRequestDto;
import com.purnima.jain.service.CustomerService;

@RestController
public class CustomerPostController {
	
	@Autowired
	private CustomerService customerService;
	
	// Sample Input (Valid: 201-Created): {     "firstName": "Purnima",     "lastName": "Jain",     "age": 93 }
	// Sample Input (Valid: 415-Unsupported Media Type): {     "firstName": "Purnima",     "lastName": "Jain",     "age": 93 }
	// Throws HttpMediaTypeNotSupportedException (415-Unsupported Media Type) when the json content is sent as application/xml or text/plain
	// Sample Input (Valid: 400-Bad Request): {     "lastName": "Jain",     "age": 93 }
	// Throws MethodArgumentNotValidException (400-Bad Request) when the @Valid annotation fails due to the violation of @NotNull & @NotBlank on the firstName in CustomerPostRequestDto
	@PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerPostRequestDto customerPostRequestDto) {
		Customer customer = customerPostRequestDto.toCustomer();
		Customer savedCustomer = customerService.saveCustomer(customer);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedCustomer.getCustomerId()).toUri();		
		
		return  ResponseEntity.created(location).build();
	}
	
		
}
