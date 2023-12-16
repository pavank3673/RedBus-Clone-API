package com.example.redbus.exception;

public class RideCannotBeCreatedForStartDateTime extends RuntimeException {

	private String message;

	public RideCannotBeCreatedForStartDateTime(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
