package com.revature.controllers;

import org.slf4j.MDC;

import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller {
	
	private UserService userService = new UserService();

	private Handler getAllUsers = (ctx) -> {
		ctx.json(userService.findAll());
		ctx.status(200);
		
		MDC.clear();
	};
	
	private Handler getSingleUser = (ctx) -> {
		String idString = ctx.pathParam("id");
		
		int id = Integer.parseInt(idString);
		
		MDC.clear();
	};
	
	private Handler createUser = (ctx) -> {
		User u = ctx.bodyAsClass(User.class);
		// Once again, requires Jackson Databind to work properly
		
//		System.out.println(u);
		
		// Save the new User in the DB
		
		MDC.clear();
	};
	
	public void addRoutes(Javalin app) {
		app.get("/users", this.getAllUsers);
		
		app.get("/users/:id", this.getSingleUser);
		
		app.post("/users", this.createUser);
	}
}
