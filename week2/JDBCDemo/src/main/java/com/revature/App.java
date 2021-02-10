package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controllers.Controller;
import com.revature.controllers.UserController;
import com.revature.exceptions.RegisterUserFailedException;
import com.revature.exceptions.handlers.ExceptionHandler;
import com.revature.exceptions.handlers.GlobalExceptionHandler;

import io.javalin.Javalin;

public class App {
	
	private static Javalin app;
	
	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		app = Javalin.create( (config) -> {
			
			config.defaultContentType = "application/json";
			
			config.enforceSsl = false;
			
			config.ignoreTrailingSlashes = true;
			
			config.enableCorsForAllOrigins();
			
			config.enableDevLogging();
		});
		
		configure(new UserController());
		
		app.start(7000);
		
		addExceptionHandlers(new GlobalExceptionHandler());
	}

	public static void configure(Controller... controllers) {
		for(Controller c : controllers) {
			c.addRoutes(app);
		}
	}
	
	public static void addExceptionHandlers(ExceptionHandler... handlers) {
		for(ExceptionHandler handler : handlers) {
			handler.addHandlers(app);
		}
	}
}
