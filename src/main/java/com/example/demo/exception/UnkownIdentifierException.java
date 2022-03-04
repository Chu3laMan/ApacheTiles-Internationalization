package com.example.demo.exception;

public class UnkownIdentifierException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3378388870166346318L;
	
	public UnkownIdentifierException(String message) {
		super(message);
	}
	
	public UnkownIdentifierException(String message, Throwable cause) {
		super(message, cause);
	}

}
