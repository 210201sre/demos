package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller {
	
	private UserService userService = new UserService();

	private Handler getAllUsers = (ctx) -> {
		List<User> users = new ArrayList<>();
		
		users.add(new User());
		
		ctx.json(users);
		ctx.status(200);
	};
	
	private Handler getSingleUser = (ctx) -> {
		String idString = ctx.pathParam("id");
		
		int id = Integer.parseInt(idString);
	};
	
	private Handler createUser = (ctx) -> {
		User u = ctx.bodyAsClass(User.class);
		// Once again, requires Jackson Databind to work properly
		
		System.out.println(u);
		
		// Save the new User in the DB
	};
	
	public void addRoutes(Javalin app) {
		app.get("/users", this.getAllUsers);
		
		app.get("/users/:id", this.getSingleUser);
		
		app.post("/users", this.createUser);
	}
}
