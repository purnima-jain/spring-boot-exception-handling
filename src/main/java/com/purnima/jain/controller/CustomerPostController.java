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

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerPostController {
	
	@Autowired
	private CustomerService customerService;
	
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
