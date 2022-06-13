package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("select c from Customer c where c.firstName = :firstName")
	public List<Customer> getCustomerByFirstName(@Param("firstName") String firstName);
	
	@Query("select c from Customer c where c.lastName = :lastName")
	public List<Customer> getCustomerByLastName(@Param("lastName") String lastName);
	
	@Query("select c from Customer c where c.age = :age")
	public List<Customer> getCustomerByAge(@Param("age") int age);
}
