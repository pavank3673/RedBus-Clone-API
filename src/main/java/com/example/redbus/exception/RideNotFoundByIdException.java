package com.example.redbus.exception;

public class RideNotFoundByIdException extends RuntimeException{

	private String message;

	public RideNotFoundByIdException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
