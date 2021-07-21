package com.deals.date.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deals.date.exception.NotFoundException;
import com.deals.date.model.Admin;
import com.deals.date.model.Customer;
import com.deals.date.repository.CustomerRepository;

//implementing CustomerService interface
@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	CustomerRepository customerRepo;

//	@Autowired
//	CartServiceImp cartService;

	// Add new customer
	@Override
	public String addCustomer(Customer c) {
		customerRepo.save(c);
		return c.getUsername() + " added successfully!!";
	}
	

	// Update existing customer
	@Override
	public Customer updateCustomer(Customer c) {
		Customer cust=customerRepo.save(c);
		return cust ;
	}

	// Delete customer
	@Override
	public String deleteCustomer(String email) {

		Customer myCust = customerRepo.findByEmail(email);
		customerRepo.delete(myCust);
		return "Customer deleted successfully!!";

	}

	// View customer by email id
	@Override
	public Customer viewCustomer(String email) {

		return customerRepo.findByEmail(email);
	}
	
	// View all  customer
	@Override
	public List<Customer> viewAllCustomers() {
		List<Customer> clist = (List<Customer>) customerRepo.findAll();
		return clist;
	}

	// Update specific customer's phone number
	@Override
	public String updateCustomerPhone(String email, String phone) {
		Customer myCustomer = customerRepo.findByEmail(email);
		myCustomer.setPhoneNo(phone);
		customerRepo.save(myCustomer);
		return "Phone number updated Successfully!!";
	}

	// Update specific customer's address
	@Override
	public String updateCustomerAddress(String email, String address) {
		Customer myCustomer = customerRepo.findByEmail(email);
		myCustomer.setAddress(address);
		customerRepo.save(myCustomer);
		return "Address updated Successfully!!";
	}

	// Update specific customer's password
	@Override
	public String updateCustomerPassword(String email, String password) {
		Customer myCustomer = customerRepo.findByEmail(email);
		myCustomer.setPassword(password);
		customerRepo.save(myCustomer);
		return "Password updated Successfully!!";
	}

	//verify customer  by password
	@Override
	public String verifyCustomer(String email, String password) throws NotFoundException {
		Customer cust=customerRepo.findByEmail(email);
		String checkpass=cust.getPassword();
		if(checkpass.equals(password)) {
			return "User verified";
		}
		else {
			throw new NotFoundException("Incorrect email or password"); 
		}
		
	}
    
	//verify customer  by phoneNo
	@Override
	public String verifyPhoneNo(String email, String phoneNo) throws NotFoundException {
		Customer cust=customerRepo.findByEmail(email);
		String checkPhoneNo=cust.getPhoneNo();
		if(checkPhoneNo.equals(phoneNo)) {
			return "Phone number verified";
		}
		else {
			throw new NotFoundException("Email and Phone Number doesnot match");
		}
	}

}