package com.revature.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.User;

@Service // The presence of this annotation will register this class as a "Spring Bean"
// Which means we can get access to it through the @Autowired annotation
public class UserService {

	public User login(String username, String password) {
		// Normally, we would have autowired in a DAO object, and used that to actually login
		
		return new User(1, username, password, "Admin");
		// Pretend we successfully logged in
	}
	
	public List<User> findAll() {
		// Pretend we queried the DB
		return Arrays.asList(new User(1, "moberlies", "password", "Admin"));
	}
}
