package com.deals.date.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deals.date.exception.CustomerExistException;
import com.deals.date.exception.IllegalFormatException;
import com.deals.date.exception.NotFoundException;
import com.deals.date.model.Customer;
import com.deals.date.repository.CustomerRepository;
import com.deals.date.service.CustomerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService service;

	@Autowired
	CustomerRepository repo;

	// String variables for validation using regular expressions.
	String emailPattern = "[a-zA-Z0-9+_.-]+@(.+)$";
	String passwordPattern = "[a-zA-Z0-9,.@_]{6,12}";
	String phoneNoPattern = "(0/91)?[7-9][0-9]{9}";
	String usernamePattern = "^[a-z0-9]{3,16}$";

	// Post method for adding new Customer
	@PostMapping("/addCustomer")
	private ResponseEntity<String> addCustomer(@RequestBody @Valid Customer c, Errors errors)
			throws IllegalFormatException, CustomerExistException {

		// Throwing IllegalFormatException if validation failes.
		if (errors.hasFieldErrors("username")) {
			throw new IllegalFormatException("Username cannot be empty!!");

		}
		if (errors.hasFieldErrors("address")) {
			throw new IllegalFormatException("Address cannot be empty!!");
		}

		// Validating using pattern
		if (!c.getEmail().matches(emailPattern)) {
			throw new IllegalFormatException("Email format is not valid!!");
		}

		if (!c.getUsername().matches(usernamePattern)) {
			throw new IllegalFormatException("Enter valid username");
		}

		if (!c.getPassword().matches(passwordPattern)) {
			throw new IllegalFormatException("Enter atleast 6-12 character!!");
		}

		if (!c.getPhoneNo().matches(phoneNoPattern)) {
			throw new IllegalFormatException("Enter valid 10 digit phone number!!");
		}

		// Checking if the customer is already existing
		if (service.viewCustomer(c.getEmail()) != null) {
			throw new CustomerExistException("Customer already exists!!");
		}

		else {
			// Saving or adding the customer
			String response = service.addCustomer(c);

			// Displaying a response message
			if (response == null) {
				return new ResponseEntity<String>("Customer not added", HttpStatus.FORBIDDEN);
			}

			else {
				return new ResponseEntity<String>(response, HttpStatus.OK);
			}
		}

	}

	// Delete method for deleting a customer
	@DeleteMapping("/deleteCustomer/{email}")
	private ResponseEntity<String> deleteCustomer(@PathVariable("email") String email) throws NotFoundException {

		// Checking if the customer exists or not
		if (service.viewCustomer(email) == null) {
			throw new NotFoundException("Customer not found!!");

		}

		else {
			// Deleting customer
			String response = service.deleteCustomer(email);

			// Displaying a response message
			if (response == null) {
				return new ResponseEntity<String>("Customer not deleted!", HttpStatus.FORBIDDEN);
			}

			else {
				return new ResponseEntity<String>(response, HttpStatus.OK);
			}
		}
	}

	// Get method for viewing all the customers
	@GetMapping("/viewAllCustomers")
	private ResponseEntity<String> viewAllCustomers() throws NotFoundException {

		// Creating list for all customers
		List<Customer> clist = (List<Customer>) repo.findAll();

		// Displaying Customers
		String cust = "";

		for (Customer customer : clist) {

			cust = cust + "Email: " + customer.getEmail() + "\nUserName: " + customer.getUsername() + "\nPassword: "
					+ customer.getPassword() + "\nAddress: " + customer.getAddress() + "\nPhoneNo: "
					+ customer.getPhoneNo() + "\n\n";
		}

		// Checking if the list is empty or not and displaying a response message
		if (clist.isEmpty())
			throw new NotFoundException("There are no Customers yet!!");

		else
			return new ResponseEntity<String>(cust, HttpStatus.OK);
	}

	// Get method for viewing customer by giving email id as input
	@GetMapping("/viewCustomer/{email}")
	private ResponseEntity<Customer> viewCustomerByEmail(@PathVariable("email") String email)
			throws NotFoundException, IllegalFormatException {

		// Validating email using pattern
		if (email.matches(emailPattern)) {

			Customer customer = service.viewCustomer(email);
			String cust = "Customer Details: \n\n";

			// Checking if customer exists or not and displaying the appropriate response
			if (customer == null)
				throw new NotFoundException("No customer with email: " + email + " found!!");

			else
				cust = cust + "Email: " + customer.getEmail() + "\nUserName: " + customer.getUsername() + "\nPassword: "
						+ customer.getPassword() + "\nAddress: " + customer.getAddress() + "\nPhoneNo: "
						+ customer.getPhoneNo() + "\n\n";
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}

		else {
			// Throwing exception if email is not valid
			throw new IllegalFormatException("Enter valid email id!!");
		}
	}

	// Put method for updating customer
	@PutMapping("/update/{email}")
	private ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer,@PathVariable("email") String email) {
		Customer c = service.viewCustomer(email);
		c.setAddress(customer.getAddress());
		c.setPhoneNo(customer.getPhoneNo());
		c.setPassword(customer.getPassword());
		return new ResponseEntity<Customer>(service.updateCustomer(c), HttpStatus.OK);
	}

	// Patch method for updating customer's phone number
	@PatchMapping("/updateCustomerPhone/{email}/{phone}")
	private String updateCustomerPhone(@PathVariable("email") String email, @PathVariable("phone") String phone)
			throws NotFoundException, IllegalFormatException {

		// Validating input email and phone number using pattern
		if (email.matches(emailPattern) && phone.matches(phoneNoPattern)) {

			// Checking if customer exists or not and displaying appropriate response
			if (service.viewCustomer(email) == null) {
				throw new NotFoundException("Customer not found!!");
			}

			else
				return service.updateCustomerPhone(email, phone);
		}

		else {
			// Throwing exception if email or phone number are not valid
			throw new IllegalFormatException("Enter valid email id or phone number for input!!");
		}
	}

	// Patch method for updating customer's address
	@PatchMapping("/updateCustomerAddress/{email}/{address}")
	private String updateCustomerAddress(@PathVariable("email") String email, @PathVariable("address") String address)
			throws NotFoundException, IllegalFormatException {

		// Validating email and address
		if (email.matches(emailPattern) && !address.isBlank()) {

			// Checking if customer exists or not and displaying appropriate response
			if (service.viewCustomer(email) == null) {
				throw new NotFoundException("Customer not found!!");
			}

			else
				return service.updateCustomerAddress(email, address);
		}

		else {
			// Throwing exception if email or address is not valid
			throw new IllegalFormatException("Enter valid email id or address for input!!");
		}
	}

	// Patch method for updating customer's password
	@PatchMapping("/updateCustomerPassword/{email}/{password}")
	private String updateCustomerPassword(@PathVariable("email") String email,
			@PathVariable("password") String password) throws NotFoundException, IllegalFormatException {

		// Validating email and password using pattern
		if (email.matches(emailPattern) && password.matches(passwordPattern)) {

			// Checking if customer exists or not and displaying appropriate response
			if (service.viewCustomer(email) == null) {
				throw new NotFoundException("Customer not found!!");
			}

			else
				return service.updateCustomerPassword(email, password);
		}

		else {
			// Throwing exception if email id or password is not valid
			throw new IllegalFormatException("Enter valid email id or password for input!!");
		}
	}

	@GetMapping("/verifyCustomer/{email}/{password}")
	private String verifyCustomer(@PathVariable("email") String email, @PathVariable("password") String password)
			throws NotFoundException {
		return service.verifyCustomer(email, password);
	}

	@GetMapping("/verifyPhoneNo/{email}/{phoneNo}")
	private String verifyPhoneNo(@PathVariable("email") String email, @PathVariable("phoneNo") String phoneNo)
			throws NotFoundException {
		return service.verifyPhoneNo(email, phoneNo);
	}

}