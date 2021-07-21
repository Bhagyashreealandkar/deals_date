package com.deals.date.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.deals.date.exception.*;
import com.deals.date.model.Cart;
import com.deals.date.model.Customer;
import com.deals.date.repository.CartRepository;
import com.deals.date.repository.CustomerRepository;
import com.deals.date.repository.ProductRepository;
import com.deals.date.service.CartServiceImp;
import com.deals.date.service.ProductService;

@RestController
@CrossOrigin(origins="*")
//@RequestMapping("/cart")
public class CartController {
	@Autowired
	ProductService service;

	// String variables for validation using regular expressions.
	String emailPattern = "[a-zA-Z0-9+_.-]+@(.+)$";

	@Autowired
	CartServiceImp cartService;
	@Autowired
	CustomerRepository custRepo;
	@Autowired
	ProductRepository prodRepo;
	@Autowired
	CartRepository cartRepo;


@GetMapping("/viewAll")
public ResponseEntity<?> viewAllCart() throws NotFoundException {
	List<Cart> c = (List<Cart>) cartRepo.findAll();
	if (c == null) {
		throw new NotFoundException("There are no Customers registered");
	}
	else {
		return new ResponseEntity<List<Map<String, String>>>(cartService.viewAll(),HttpStatus.OK);
	}
	
}
@GetMapping("/viewCart/{email}")
public List<Map<String, String>> viewCartByEmail(@PathVariable("email") String email) throws NotFoundException {
	if(custRepo.findByEmail(email) == null) {
		throw new NotFoundException("There are no Customers registered");
	}
	return cartService.viewByEmail(email);
}

@PostMapping("/addToCart/{email}/{prodId}")
		public void addToCart(@PathVariable("email") String email,
				@PathVariable("prodId") int prodId) throws NotFoundException {
	if(custRepo.findByEmail(email) == null) {
		throw new NotFoundException("There are no Customers registered");
	}
	else if(prodRepo.findByprodId(prodId) == null) {
		throw new NotFoundException("Product not found");
	}
			cartService.addToCart(email, prodId);
	}

	@DeleteMapping("/removeProdFromCart/{email}/{prodName}")
	public void removeProdFromCart(@PathVariable("email") String email,@PathVariable("prodName") String prodName) throws NotFoundException {
		if(custRepo.findByEmail(email) == null) {
			throw new NotFoundException("There are no Customers registered");
		}
		cartService.removeProdFromCart(email, prodName);
	}
	@PutMapping("/updateQtyOfProd/{email}/{prodName}/{qty}")
	public void updateQtyOfProd(@PathVariable("email") String email,@PathVariable("prodName") String prodName,@PathVariable("qty") int qty) throws NotFoundException {
		if(custRepo.findByEmail(email) == null) {
			throw new NotFoundException("There are no Customers registered");
		}
		cartService.updateQtyOfProdInCart(email, prodName, qty);
	}
}