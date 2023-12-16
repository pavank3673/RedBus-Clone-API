package com.example.redbus.exception;

public class RideDeletedException extends RuntimeException {

	private String message;

	public RideDeletedException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
