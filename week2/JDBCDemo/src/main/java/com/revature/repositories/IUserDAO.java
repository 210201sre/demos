package com.revature.repositories;

import java.util.List;

import com.revature.models.User;

// Standard CRUD operations as well as anything you might find useful
public interface IUserDAO {

	public List<User> findAll();
	public User findByUsername(String username);
	public int insert(User u); // Returns the generated primary key
//	public User insert(User u); // Returns the modified User object with the generated Primary Key
	public boolean update(User u); // Returns whether the update was successful or not
	// For example, it might have violated some constraint
	public boolean delete(int userId); // Returns whether the record was actually deleted
	public User findById(int userId);
}
