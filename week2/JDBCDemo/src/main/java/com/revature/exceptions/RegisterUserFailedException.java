package com.revature.exceptions;

public class RegisterUserFailedException extends RuntimeException {

	public RegisterUserFailedException() {
	}

	public RegisterUserFailedException(String message) {
		super(message);
	}

	public RegisterUserFailedException(Throwable cause) {
		super(cause);
	}

	public RegisterUserFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegisterUserFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
