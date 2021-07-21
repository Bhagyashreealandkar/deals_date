package com.deals.date.service;

import com.deals.date.model.Customer;

//Defining an interface for the services of the Customer class
public interface CartService {
	
	// Defining all the methods for the operations on Customer class
	
	public String addToCart(Customer c);

	public Customer findByEmail(String email);

	public String removeProductFromCart(Customer c);

	public String removeAllProducts(Customer c);

	public String printCustomer(Customer c);
}
