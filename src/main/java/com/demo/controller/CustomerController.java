package com.demo.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Customer;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.CustomerRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping(path = "/customers")
	public List<Customer> getAllCustomer(){
		return customerRepository.findAll();
	}
	
	@GetMapping(path = "/customer/id/{id}")
	public List<Customer> getCustomerById(@PathVariable("id") long id) {
		return Arrays.asList(customerRepository.findById(id).get());
	}
	
	@GetMapping(path = "/customer/firstName/{firstName}")
	public List<Customer> getCustomerByFirstName(@PathVariable("firstName") String firstName){
		return customerRepository.getCustomerByFirstName(firstName);
	}
	
	@GetMapping(path = "/customer/lastName/{lastName}")
	public List<Customer> getCustomerByLastName(@PathVariable("lastName") String lastName){
		return customerRepository.getCustomerByLastName(lastName);
	}
	
	@GetMapping(path = "/customer/age/{age}")
	public List<Customer> getCustomerByAge(@PathVariable("age") int age){
		return customerRepository.getCustomerByAge(age);
	}
	
	@PostMapping(path = "/customers")
	public List<Customer> createCustomer(@RequestBody Customer customer) {
		return Arrays.asList(customerRepository.save(customer));
	}
	
	@PutMapping("/customers/update/{id}")
	public List<Customer> updateEmployee(@PathVariable(value = "id") Long id,
			@RequestBody Customer dest) throws ResourceNotFoundException {
		Customer src = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));

		src.setFirstName(dest.getFirstName());
		src.setLastName(dest.getLastName());
		src.setAge(dest.getAge());
		return Arrays.asList(customerRepository.save(src));
	}

	@DeleteMapping("/customers/delete/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Customer src = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));

		customerRepository.delete(src);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
