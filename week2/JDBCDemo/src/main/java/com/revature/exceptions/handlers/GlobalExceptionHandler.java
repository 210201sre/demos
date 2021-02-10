package com.revature.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.exceptions.RegisterUserFailedException;

import io.javalin.Javalin;

public class GlobalExceptionHandler implements ExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	private io.javalin.http.ExceptionHandler<RegisterUserFailedException> handleRegisterException = (e, ctx) -> {
		
		// Custom Exception Handling logic
		
		// Log what went wrong
		log.error("Failed to register new User", e);
		
		// Inform the user that something went wrong
		// Give however much information you are willing about the reason
		ctx.status(500);
		ctx.result("Something went wrong");
	};

	public GlobalExceptionHandler() {
		super();
	}

	@Override
	public void addHandlers(Javalin app) {
		app.exception(RegisterUserFailedException.class, this.handleRegisterException);
	}

}
