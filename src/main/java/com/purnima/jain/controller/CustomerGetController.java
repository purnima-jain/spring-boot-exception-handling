package com.purnima.jain.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.purnima.jain.domain.Customer;
import com.purnima.jain.exception.apierror.CustomValidationError;
import com.purnima.jain.exception.custom.CustomValidationViolationException;
import com.purnima.jain.exception.custom.ResourceNotFoundException;
import com.purnima.jain.json.CustomerGetResponseDto;
import com.purnima.jain.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerGetController {
	
	@Autowired
	private CustomerService customerService;
	
	// Sample Input (Valid: 200-Ok): http://localhost:8080/customers/1408
	// Sample Input: http://localhost:8080/customers/      
	// Sample Input: http://localhost:8080/customers/abcd
	// Sample Input: http://localhost:8080/customers/12345
	// Sample Input (Valid: 404-Not Found): http://localhost:8080/customers/1409
	@GetMapping(value = "/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerGetResponseDto> getCustomerById(@PathVariable("customerId") String customerId) {
		log.info("Input Customer Id :: {}", customerId);
		
		validateCustomerId(customerId);
		
		Optional<Customer> customerOptional = customerService.getCustomerById(Integer.parseInt(customerId));
		
		Customer customer = customerOptional.orElseThrow(() -> new ResourceNotFoundException(Customer.class, "customerId", customerId.toString(), "customerName", "*"));
		CustomerGetResponseDto customerGetResponseDto = new CustomerGetResponseDto(customer);
		return ResponseEntity.ok().body(customerGetResponseDto);
	}
	
	private void validateCustomerId(String customerId) {
		List<CustomValidationError> customValidationErrorList = new ArrayList<>();
		
		if(customerId.isBlank()) {
			customValidationErrorList.add(new CustomValidationError("Customer", "CustomerId", customerId, "CustomerId cannot be blank"));
			throw new CustomValidationViolationException(customValidationErrorList);
		}
		
		Integer intCustomerId = null;
		try {
			intCustomerId = Integer.parseInt(customerId);
		} catch(NumberFormatException nfe) {
			customValidationErrorList.add(new CustomValidationError("Customer", "CustomerId", customerId, "CustomerId has to be an Integer"));
		}
		
		if(intCustomerId != null && intCustomerId.toString().length() != 4) {
			customValidationErrorList.add(new CustomValidationError("Customer", "CustomerId", customerId, "CustomerId has to be four characters long"));
		}
		
		if(intCustomerId != null && intCustomerId > 9999) {
			customValidationErrorList.add(new CustomValidationError("Customer", "CustomerId", customerId, "CustomerId cannot be greater than 9999"));
		}
		
		if(!customValidationErrorList.isEmpty()) {
			throw new CustomValidationViolationException(customValidationErrorList);
		}
	}
	
//	@GetMapping(value = "/customers/collection")
//    public ResponseEntity<List<CustomerGetResponseDto>> getCustomerCollection(@RequestBody CustomerGetRequestSearchCriteriaCustomerIdsDto customerGetRequestSearchCriteriaCustomerIdsDto) {
//		List<Customer> customers = customerService.getCustomerCollection(customerGetRequestSearchCriteriaCustomerIdsDto.getCustomerIds());
//		List<CustomerGetResponseDto> customerGetResponseDtoList = customers.stream().map(customer -> new CustomerGetResponseDto(customer)).collect(Collectors.toList());
//		
//		return ResponseEntity.ok().body(customerGetResponseDtoList); 
//    }
	
	// Sample Input (Valid: 200-Ok): http://localhost:8080/customers/collection?firstName=Eden
	// Sample Input (Valid: 400-Bad Request): http://localhost:8080/customers/collection
	// Throws MissingServletRequestParameterException if "required" "@RequestParam" firstName is not present
	@GetMapping(value = "/customers/collection")
	public ResponseEntity<List<CustomerGetResponseDto>> getCustomerCollectionByFirstName(@RequestParam(name = "firstName", required = true) String firstName) {
		List<Customer> customers = customerService.getCustomerCollectionByFirstName(firstName);
		List<CustomerGetResponseDto> customerGetResponseDtoList = customers.stream().map(customer -> new CustomerGetResponseDto(customer)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(customerGetResponseDtoList); 
	}
}
