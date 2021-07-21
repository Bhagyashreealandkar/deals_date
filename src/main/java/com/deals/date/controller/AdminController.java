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
import com.deals.date.model.Admin;
import com.deals.date.model.Customer;
import com.deals.date.service.AdminService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService service;

	// String variables for validation using regular expressions.
	String emailPattern = "[a-zA-Z0-9+_.-]+@(.+)$";
	String passwordPattern = "[a-zA-Z0-9,.@_]{6,12}";
	String phoneNoPattern = "(0/91)?[7-9][0-9]{9}";

	// Post method for adding new Admin
		@PostMapping("/addAdmin")
		private ResponseEntity<?> addAdmin(@RequestBody @Valid Admin a,  Errors errors) throws IllegalFormatException,CustomerExistException 
		{
			// Validating using pattern		
			if (!a.getEmail().matches(emailPattern)) {
	     		throw new IllegalFormatException("Email format is not valid!!");
			}

			if (!a.getPassword().matches(passwordPattern)) {
				throw new IllegalFormatException("Enter password atleast of 6-12 character!!");
			}
			
			if (!a.getPhoneNo().matches(phoneNoPattern)) {
				throw new IllegalFormatException("Enter valid 10 digit phone number!!");
			}

			// Checking if the admin is already existing
			if (service.viewAdmin(a.getEmail()) != null) {
				throw new CustomerExistException("Admin already exists!!");
			}

			else {
				// Saving or adding the admin
				Admin response = service.addAdmin(a);
				// Displaying a response message
				if (response == null) {
					return new ResponseEntity<String>("Admin not added", HttpStatus.FORBIDDEN);
				}

				else {
					String result=response.getEmail() + " added successfully!!";
					return new ResponseEntity<String>(result, HttpStatus.OK);
				}
			}

		}
		
	// Get method for viewing all the admin
	@GetMapping("/viewAllAdmin")
	private ResponseEntity<String> viewAllAdmin() throws NotFoundException {

		// Creating list for all admin
		List<Admin> alist = service.viewAllAdmins();
		// Checking if the list is empty or not and displaying a response message
		if (alist.isEmpty())
			throw new NotFoundException("There are no Admin yet!!");

		else {
			String adm = "List of Admin: \n\n";

			for (Admin admin : alist) {
				adm = adm + "\nEmail: " + admin.getEmail() + "\nUserName: " + admin.getPhoneNo() + "\nPassword: "
						+ admin.getPassword()+"\n";
			}
			return new ResponseEntity<String>(adm, HttpStatus.OK);
		}

	}
	
	// Delete method for deleting a admin
		@DeleteMapping("/deleteAdmin/{email}")
		private ResponseEntity<String> deleteAdmin(@PathVariable("email") String email) throws NotFoundException {

			// Checking if the customer exists or not
			if (service.viewAdmin(email) == null) {
				throw new NotFoundException("Admin not found!!");

			}

			else {
				// Deleting admin
				String response = service.deleteAdmin(email);

				// Displaying a response message
				if (response == null) {
					return new ResponseEntity<String>("Admin not deleted!", HttpStatus.FORBIDDEN);
				}

				else {
					return new ResponseEntity<String>(response, HttpStatus.OK);
				}
			}
		}

	// Get method for viewing admin by giving email id as input
	@GetMapping("/viewAdmin/{email}")
	private ResponseEntity<String> viewAdminByEmail(@PathVariable("email") String email)
			throws NotFoundException, IllegalFormatException {

		// Validating email using pattern
		if (email.matches(emailPattern)) {
			Admin admin = service.viewAdmin(email);

			// Checking if admin exists or not and displaying the appropriate response
			if (admin == null)
				throw new NotFoundException("No admin with email: " + email + " found!!");

			else {
				String adm = "Admin Details: \n\n";
				adm = adm + "Email: " + admin.getEmail() + "\nUserName: " + admin.getPhoneNo() + "\nPassword: "
						+ admin.getPassword();
				return new ResponseEntity<String>(adm, HttpStatus.OK);
			}

		}

		else {
			// Throwing exception if email is not valid
			throw new IllegalFormatException("Enter valid email id!!");
		}
	}
	
	//Put method for updating admin
		@PutMapping("/update/{email}")
		private ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin,@PathVariable("email") String email) {
			Admin a = service.viewAdmin(email);
			a.setPhoneNo(admin.getPhoneNo());
			a.setPassword(admin.getPassword());
			return new ResponseEntity<Admin>(service.updateAdmin(a), HttpStatus.OK);
		}

	// Patch method for update admin
	@PatchMapping("/updateAdminPassword/{email}/{password}/{phoneNo}")
	private String updateAdminPassword(@PathVariable("email") String email, @PathVariable("password") String password,
			@PathVariable("phoneNo") String phoneNo) throws NotFoundException, IllegalFormatException {

		// Validating email and password using pattern
		if (email.matches(emailPattern) && password.matches(passwordPattern)) {

			// Checking if admin exists or not and displaying appropriate response
			if (service.viewAdmin(email) == null) {
				throw new NotFoundException("Admin not found!!");
			}

			else
				return service.updateAdminPassword(email, phoneNo, password);
		}

		else {
			// Throwing exception if email id or password is not valid
			throw new IllegalFormatException("Enter valid email id or password for input!!");
		}
	}

	// Get method for viewing all the feedback by admin
	@GetMapping("/viewAllFeedback")
	private ResponseEntity<String> viewAllFeedback() throws NotFoundException {

		// Creating list for all admin
		String feedback = service.viewAllFeedback();

		// Checking if the list is empty or not and displaying a response message
		if (feedback.isEmpty())
			throw new NotFoundException("There are no feedback yet!!");

		else
			return new ResponseEntity<String>(feedback, HttpStatus.OK);
	}

	// Get method for viewing specific feedback by amdin 
	@GetMapping("/viewFeedbackById/{email}")
	private ResponseEntity<String> viewFeedbackbyId(@PathVariable(value = "email") String email)
			throws NotFoundException {

		// Creating list for all admin
		String feedback = service.viewFeedbackById(email);

		// Checking if the list is empty or not and displaying a response message
		if (feedback.isEmpty())
			throw new NotFoundException("There are no feedback yet!!");

		else
			return new ResponseEntity<String>(feedback, HttpStatus.OK);
	}
	
	//verify admin by password
	@GetMapping("/verifyAdmin/{email}/{password}")
	private String verifyAdmin(@PathVariable("email") String email,@PathVariable("password") String password) throws NotFoundException {
		return service.verifyAdmin(email, password);
	}
	
	//verify admin by phoneNo
	@GetMapping("/verifyPhoneNo/{email}/{phoneNo}")
	private String verifyPhoneNo(@PathVariable("email") String email,@PathVariable("phoneNo") String phoneNo) throws NotFoundException {
		return service.verifyPhoneNo(email, phoneNo);
	}
}