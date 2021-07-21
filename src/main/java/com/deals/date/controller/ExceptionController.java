package com.deals.date.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.deals.date.exception.*;

//create class ExceptionController to handle exceptions
@ControllerAdvice
public class ExceptionController {
	// Method to handle IllegalFormatException
	@ExceptionHandler(value = IllegalFormatException.class)
	public ResponseEntity<Object> IllegalFormatException(IllegalFormatException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}

	// Method to handle NotFoundException
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> NotFoundException(NotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	// Method to handle CustomerExistException
	@ExceptionHandler(value = CustomerExistException.class)
	public ResponseEntity<Object> CustomerExistException(CustomerExistException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	
}
