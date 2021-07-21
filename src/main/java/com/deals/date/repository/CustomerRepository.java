package com.deals.date.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deals.date.model.Admin;
import com.deals.date.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

	// Find customer by email id
	Customer findByEmail(String email);
}