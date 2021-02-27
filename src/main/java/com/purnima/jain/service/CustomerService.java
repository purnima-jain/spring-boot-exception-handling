package com.purnima.jain.service;

import java.util.List;
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
	
//	public List<Customer> getCustomerCollection(List<Integer> customerIds) {
//		List<Customer> customers = new ArrayList<>();
//
//        for (Integer customerId : customerIds) {
//        	Customer customer = customerRepository.getCustomerById(customerId).orElse(null);
//            if (customer != null) {
//            	customers.add(customer);
//            }
//        }
//        return customers;
//	}
	
	public List<Customer> getCustomerCollectionByFirstName(String firstName) {
		return customerRepository.getCustomersByFirstName(firstName);
	}	
}
