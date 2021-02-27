package com.purnima.jain.repo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.purnima.jain.domain.Customer;
import com.purnima.jain.entity.CustomerEntity;
import com.purnima.jain.repo.jpa.CustomerJpaRepository;

@Repository
public class CustomerRepository {
	
	@Autowired
	private CustomerJpaRepository customerJpaRepository;

	public Optional<Customer> getCustomerById(Integer customerId) {
		Optional<CustomerEntity> customerEntity = customerJpaRepository.findById(customerId);
		return customerEntity.map(this::customerEntityToCustomer);
	}

	private Customer customerEntityToCustomer(CustomerEntity customerEntity) {
		Customer customer = new Customer();
		customer.setCustomerId(customerEntity.getCustomerId());
		customer.setFirstName(customerEntity.getFirstName());
		customer.setLastName(customerEntity.getLastName());
		customer.setAge(customerEntity.getAge());

		return customer;
	}
	
	public Customer saveCustomer(Customer customer) {
		CustomerEntity customerEntity = customerToCustomerEntity(customer);		
		CustomerEntity savedCustomerEntity = customerJpaRepository.save(customerEntity);
		
		return customerEntityToCustomer(savedCustomerEntity);
	}
	
	private CustomerEntity customerToCustomerEntity(Customer customer) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setCustomerId(customer.getCustomerId());
		customerEntity.setFirstName(customer.getFirstName());
		customerEntity.setLastName(customer.getLastName());
		customerEntity.setAge(customer.getAge());
		
		return customerEntity;		
	}
	
	public List<Customer> getCustomersByFirstName(String firstName) {
		List<CustomerEntity> customerEntity = customerJpaRepository.findByFirstName(firstName);
		return customerEntity.stream().map(this::customerEntityToCustomer).collect(Collectors.toList());
	}


}
