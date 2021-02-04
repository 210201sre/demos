package com.revature.exceptions;

public class NegativeValueException extends RuntimeException {

	private static final long serialVersionUID = -543690355120075277L;

	public NegativeValueException() {
		super();
	}

	public NegativeValueException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NegativeValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public NegativeValueException(String message) {
		super(message);
	}

	public NegativeValueException(Throwable cause) {
		super(cause);
	}
}
