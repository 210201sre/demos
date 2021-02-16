package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.models.User;
import com.revature.services.UserService;

@Controller
@RequestMapping("/users") // Will act as a URL prefix for all requests in this class
public class UserController {

	@Autowired
	private UserService userService;
	// Grab an instance of our UserService from our Spring Container
	
	@ResponseBody
	@GetMapping
	public ResponseEntity<List<User>> findAll(@RequestParam("key") String value) {
		System.out.println(value);
		return ResponseEntity.ok(userService.findAll());
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUsername(@PathVariable(name = "username") String username) {
		
		List<User> allUsers = userService.findAll();
		
		User user = allUsers.stream()
			.reduce(null, (u1, u2) -> u2.getUsername().equals(username) ? u2 : u1);
		// I am condensing this list down to a single User object
		// By starting with null, and for every User remaining in the list, I will check if that user has the right username,
		// if it does, I swap to that User object
		// Otherwise, I keep with what I already had
		
		if(user == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<User> insert(@RequestBody User u) {
		// @RequestBody annotation will interpret the body of the incoming request to be of some type
		// Uses Jackson Databind
		
		// Actually use your userService to insert the new User object
		
		// I will pretend I did it
		u.setId(2);
		
		return ResponseEntity.accepted().body(u);
	}
}
