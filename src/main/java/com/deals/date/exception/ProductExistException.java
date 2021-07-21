package com.deals.date.exception;

//define class to handle VegetableExistException
public class ProductExistException extends Exception {
	public ProductExistException() {
		super();
	}

	public ProductExistException(String message) {
		super(message);
	}
}
