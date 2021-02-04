package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDAO;

/*
 * The purpose of this class will be to contain all of the relevant business logic
 * relating to Users
 * 
 * For example, any decision making, any logging that is tied to important metrics
 * Will contain all of the relevant features for your application
 */
public class UserService {
	
	private UserDAO userDAO = new UserDAO();

	// If the login is successful, return the relevant User data
	// Otherwise, might throw a custom exception, to be handled elsewhere
	// Or maybe just return null
	public User login(String username, String password) {
		// TODO: Implement Properly
		return null;
	}
}
