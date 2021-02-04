package com.revature;

import java.util.ArrayList;
import java.util.List;

import com.revature.controllers.Controller;
import com.revature.controllers.UserController;
import com.revature.models.User;

import io.javalin.Javalin;

public class App {
	
	private static Javalin app;

	public static void main(String[] args) {

		//basicSetup();
//		configuredSetup();
		
		app = Javalin.create();
		
		configure(new UserController());
	}
	
	public static void basicSetup() {
		// Create a simple Javalin object
		Javalin app = Javalin.create();

		// Start the embedded web server (Jetty) on a particular port
		app.start(); // Default port 7000
		
		// We are defining a Lambda, which is an anonymous function
		// It receives a single parameter, named "ctx", which will be of type
		// Context (from the Javalin library)
		app.get("/hello", (ctx) -> {
			ctx.result("Hello World!");
		});
	}
	
	public static void configuredSetup() {
		// Can optionally pass in a handler that leverages a JavalinConfig object
		// to configure the web application
		
		app = Javalin.create( (config) -> {
			config.enableCorsForAllOrigins();
			// CORS stands for Cross Origin Resource Sharing
			// It is a protective mechanism built into browsers (not postman)
			// It restricts resources to be only allowed by web pages on the same domain
			// Unless specifically configured to allow cross-domain sharing, like the
			// above method
			
			// CORS configuration gets more complicated when you want setup authorization
			// Since generally you are not allowed to specify all origins and do
			// authorization at the same time
			// In that case, you must specify specific origins
			
			config.addStaticFiles("/frontend");
			// This would be a folder that contains static html, css, and javascript
			// for a website
		});
		
		app.start();
		
		app.get("/hello", (ctx) -> {
			ctx.result("Hello there!");
			
			ctx.status(418);
		});
		
		// Route to handle a GET request for all users
		app.get("/users", (ctx) -> {
			
			List<User> users = new ArrayList<>();
			
			users.add(new User());
			
			ctx.json(users);
			ctx.status(200);
		});
		
		// GET a single User
		// Path Params are indicated with ':paramName' in the path
		app.get("/users/:id", (ctx) -> {
			String idString = ctx.pathParam("id");
			
			int id = Integer.parseInt(idString);
			
			// Search your DB for a User that matches the provided ID
		});
		
		// POST (Create) a User
		app.post("/users", (ctx) -> {
			User u = ctx.bodyAsClass(User.class);
			// Once again, requires Jackson Databind to work properly
			
			System.out.println(u);
			
			// Save the new User in the DB
		});
		
		// Support updates with PUT requests
		// Support deletes with DELETE requests
		
		// Other categories of requests
	}
	
	public static void configure(Controller... controllers) {
		for(Controller c : controllers) {
			c.addRoutes(app);
		}
	}
}
