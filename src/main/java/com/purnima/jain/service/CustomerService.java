package com.purnima.jain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purnima.jain.domain.Customer;
import com.purnima.jain.repo.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Optional<Customer> getCustomerById(Integer customerId) {
		Optional<Customer> customerOptional = customerRepository.getCustomerById(customerId);
		return customerOptional;
	}
	
	public Customer saveCustomer(Customer customer) {
		Customer savedCustomer = customerRepository.saveCustomer(customer);
		return savedCustomer;
	}
}
