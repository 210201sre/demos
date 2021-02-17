package com.revature.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;

@RestController // This annotation is the same as @Controller
// Except that it automatically puts @ResponseBody on every public method (that is mapped) in this class
@RequestMapping("/users") // Provide some prefix for request URIs
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<Set<User>> findAll() {
//		return ResponseEntity.ok(userService.findAll());
		
		Set<User> allUsers = userService.findAll();
		
		if(allUsers.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(allUsers);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUsername(@PathVariable("username") String username) {
		
		return ResponseEntity.ok(userService.findByUsername(username));
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User u) {
		return ResponseEntity.ok(userService.insert(u));
	}
}
