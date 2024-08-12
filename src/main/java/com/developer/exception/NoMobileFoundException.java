package com.developer.exception;

public class NoMobileFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoMobileFoundException(String message) {
		super(message);
	}
}
