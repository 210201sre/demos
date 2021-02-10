package com.revature.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.revature.exceptions.RegisterUserFailedException;
import com.revature.models.User;
import com.revature.repositories.IUserDAO;
import com.revature.repositories.UserDAO;

public class UserService {

	private IUserDAO userDAO = new UserDAO();
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	public User register(User u) {
		
		MDC.put("event", "Register");
		
		log.info("Registering new User");
		
		if(u.getId() != 0) {
			// Throw a custom exception
			
			// Malformed data, new users should not already have an ID
			
//			MDC.put(null, u);
			// Can include any other information that would be helpful in your logs
			
			throw new RegisterUserFailedException("Received User object did not have ID = 0");
		}
		// Check edge cases, make sure data is good to go
		
		// Insert the new User Record
		int generatedId = userDAO.insert(u);
		
		if(generatedId != -1 && generatedId != u.getId()) {
			// Fairly confident that the INSERT was successful
			
			u.setId(generatedId);
		}
		
		
		MDC.put("userId", Integer.toString(u.getId()));
		log.info("Successfully registered User");
		
		return u;
	}
	
	// Can also have CRUD operations if they will be needed by the Controller layer
	public List<User> findAll() {
		return userDAO.findAll();
	}
}
