package com.example.redbus.exception;

public class UnAuthorizedAccessException extends RuntimeException{

	private String message;

	public UnAuthorizedAccessException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
