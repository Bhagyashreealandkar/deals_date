package com.deals.date.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deals.date.exception.*;
import com.deals.date.model.Customer;
import com.deals.date.model.Order;
//import com.deals.date.model.Payment;
import com.deals.date.repository.OrderRepository;
//import com.deals.date.repository.PaymentRepository;
import com.deals.date.service.CustomerService;
import com.deals.date.service.OrderService;
//import com.deals.date.service.PaymentService;
import com.deals.date.service.ProductService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/order")

public class OrderController {
	
	@Autowired
	OrderService service;

	@Autowired
	CustomerService custService;

	@Autowired
	ProductService productService;

	@Autowired
	OrderRepository orderRepository;
	
	
	// String variable for validation using regular expressions.
	String emailPattern = "[a-zA-Z0-9+_.-]+@(.+)$";


	
	@GetMapping("/viewOrderByEmail/{email}")
	public ResponseEntity<?> viewOrderByEmail(@PathVariable("email") String email) throws NotFoundException {

		// Finding the payment data using Id and storing in new payment object
		List<Order> order = orderRepository.findByemail(email);
		List<Map<String, String>> payString = service.viewOrder(email);
		// Checking if the payment exists or not
		if (order.isEmpty()) {
			throw new NotFoundException("order not found"); // Throwing NotFoundException if validation fails.
		} else {
			return new ResponseEntity<List<Map<String, String>>>(payString, HttpStatus.OK); // returns payment with appropriate response
		}
	}

	@GetMapping("/viewOrderByDate/{date}")
	public ResponseEntity<?> viewOrderByDate(@PathVariable("date") String date) throws NotFoundException {

		// Finding the payment data using Id and storing in new payment object
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

		  //convert String to LocalDate
		  LocalDate localDate = LocalDate.parse(date, formatter);
		
		List<Map<String, String>> payString = service.findOrderByDate(localDate);
			return new ResponseEntity<List<Map<String, String>>>(payString, HttpStatus.OK); // returns payment with appropriate response
	}

	
	// Get method for viewing all payments
	@GetMapping("/viewAllOrder")
	public ResponseEntity<?> viewAllOrders() throws NotFoundException {

		// Finding the data of all payments and storing in new payment object
		List<Map<String, String>> payString = service.viewAllOrders();
			return new ResponseEntity<List<Map<String, String>>>(payString, HttpStatus.OK); // returns payment with appropriate response
		}
	
	@DeleteMapping("/cancelOrder/{orderId}")
	public ResponseEntity<String> cancelOrder(@PathVariable("orderId") int orderId){
		String response=service.cancelOrder(orderId);
		if(response==null)
			return new ResponseEntity<String>("Order can be cancelled only within 10 days!", HttpStatus.FORBIDDEN);
		else
			return new ResponseEntity<String>(response,HttpStatus.OK);
	}

}
