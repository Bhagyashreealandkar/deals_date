package com.deals.date.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deals.date.exception.IllegalFormatException;
import com.deals.date.exception.NotFoundException;
import com.deals.date.model.Customer;
import com.deals.date.model.Payment;
import com.deals.date.model.Product;
import com.deals.date.repository.PaymentRepository;
import com.deals.date.service.CustomerService;
import com.deals.date.service.PaymentService;
import com.deals.date.service.ProductService;

//Defining a Rest Controller and mapping it to URL

@RestController
@RequestMapping("/payment")

@CrossOrigin(origins = "*")
public class PaymentController {

	@Autowired
	PaymentService service;

	@Autowired
	CustomerService custService;

	@Autowired
	ProductService prodService;

	@Autowired
	PaymentRepository paymentRepository;

// String variable for validation using regular expressions.
	String emailPattern = "[a-zA-Z0-9+_.-]+@(.+)$";

// Post method for doing payment by using email and quantity as inputs
	@GetMapping("/makePayment/{email}")
	public ResponseEntity<String> makePayment(@PathVariable(value = "email") String email)
			throws IllegalFormatException, NotFoundException {

// Finding the customer data using email and storing in new customer object
		Customer c = custService.viewCustomer(email);

// Validating using pattern
		if (!email.matches(emailPattern)) {
			throw new IllegalFormatException("Enter valid email Id!!"); // Throwing IllegalFormatException if validation
// fails.
		}

// Checking if the customer exists or not
		if (c == null) {
			throw new NotFoundException("Customer doesnot exist"); // Throwing NotFoundException if validation fails.
		} else {
			String addPayment = service.makePayment(email); // Storing the result of makePayment function in
// String addPayment
			return new ResponseEntity<String>(addPayment, HttpStatus.OK); // returns addPayment with appropriate
																			// response

		}

	}

	@GetMapping("/viewPaymentByEmail/{email}")
	public ResponseEntity<?> viewPayment(@PathVariable("email") String email) throws NotFoundException {

// Finding the payment data using Id and storing in new payment object
		List<Payment> payment = paymentRepository.searchPaymentByEmail(email);
		String payString = "";
// Checking if the payment exists or not
		if (payment == null) {
			throw new NotFoundException("payment not found"); // Throwing NotFoundException if validation fails.
		} else {
			for (Payment pay : payment) {
				payString = payString + "\nPayment Id: " + pay.getPaymentId() + "\nTotalAmount: " + pay.getTotalAmount()
						+ "\nEmail: " + pay.getEmail();
			}
			return new ResponseEntity<String>(payString, HttpStatus.OK); // returns payment with appropriate response
		}
	}

	@GetMapping("/viewPaymentByDate/{date}")
	public ResponseEntity<?> viewPaymentByDate(@PathVariable("date") String date) throws NotFoundException {

// Finding the payment data using Id and storing in new payment object
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

//convert String to LocalDate
		LocalDate localDate = LocalDate.parse(date, formatter);

		List<Payment> payment = paymentRepository.findPaymentByDate(localDate);
		String payString = "";
// Checking if the payment exists or not
		if (payment == null) {
			throw new NotFoundException("payment not found"); // Throwing NotFoundException if validation fails.
		} else {
			for (Payment pay : payment) {
				payString = payString + "\nPayment Id: " + pay.getPaymentId() + "\nTotalAmount: " + pay.getTotalAmount()
						+ "\nEmail: " + pay.getEmail();
			}
			return new ResponseEntity<String>(payString, HttpStatus.OK); // returns payment with appropriate response
		}
	}

// Get method for viewing all payments
	@GetMapping("/viewAllPayment")
	public ResponseEntity<?> getPaymentList() throws NotFoundException {

// Finding the data of all payments and storing in new payment object
		List<Payment> paymentList = paymentRepository.findAll();
		String payString = "";

// Checking if the payment exists or not
		if (paymentList.isEmpty())
			throw new NotFoundException("No payments made yet!!"); // Throwing NotFoundException if validation fails.
		else {
// for (Payment payment : paymentList) {
// payString = payString + "\nPayment Id: " + payment.getPaymentId() + "\nTotalAmount: "
// + payment.getTotalAmount() + "\nEmail: "
// + payment.getEmail() + "\n";
// }
			return new ResponseEntity<List<Payment>>(paymentList, HttpStatus.OK); // returns payment with appropriate
																					// response
		}
	}

	@GetMapping("/viewMyListOfProds/{email}")
	public List<Map<String, String>> viewMyListOfProds(@PathVariable("email") String email) {
		return service.viewMyListOfProds(email);
	}
}