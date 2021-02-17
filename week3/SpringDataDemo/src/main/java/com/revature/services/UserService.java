package com.revature.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public Set<User> findAll() {
		return userDAO.findAll()
				.stream()
				.collect(Collectors.toSet());
	}
	
	public User findById(int id) {
		return userDAO.findById(id)
				.orElseThrow( () -> new UserNotFoundException("No user found with id " + id));
	}
	
	public User findByUsername(String username) {
		return userDAO.findByUsername(username)
				.orElseThrow( () -> new UserNotFoundException("No user found with username " + username));
	}
	
	public User insert(User u) {
		return userDAO.save(u);
	}
}
