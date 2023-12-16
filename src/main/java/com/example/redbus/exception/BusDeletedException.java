package com.example.redbus.exception;

public class BusDeletedException extends RuntimeException {

	
	private String message;
	
	public BusDeletedException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
