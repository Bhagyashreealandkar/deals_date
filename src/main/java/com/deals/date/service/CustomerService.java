package com.deals.date.service;

import java.util.List;

import com.deals.date.exception.NotFoundException;
import com.deals.date.model.Customer;

//Defining an interface for the services of the Customer class
public interface CustomerService {
	
	// Defining all the methods for the operations on Customer class

	public String addCustomer(Customer c);

	public Customer updateCustomer(Customer c);

	public String deleteCustomer(String email);

	public Customer viewCustomer(String email);

	public String updateCustomerPhone(String email, String phone);

	public String updateCustomerAddress(String email, String address);

	public String updateCustomerPassword(String email, String password);
	
	public String verifyCustomer(String email,String password) throws NotFoundException;
	
	public String verifyPhoneNo(String email,String phoneNo) throws NotFoundException;

	List<Customer> viewAllCustomers();
}