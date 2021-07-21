package com.deals.date.exception;

//define class to handle CustomerExistException
public class CustomerExistException extends Exception {
	public CustomerExistException() {
		super();
	}

	public CustomerExistException(String message) {
		super(message);
	}
}
