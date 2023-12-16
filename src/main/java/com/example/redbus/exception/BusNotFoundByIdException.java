package com.example.redbus.exception;

public class BusNotFoundByIdException extends RuntimeException {

	
	private String message;
	
	public BusNotFoundByIdException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
