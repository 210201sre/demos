package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.User;

// We have an interface that is extending another interface
// It works in the same way as classes extending other classes
// except that we can still perform multiple-inheritance with interfaces
public interface UserDAO extends JpaRepository<User, Integer> {
	// Our interface will inherit all of the methods in the JpaRepository
	// That interface contains many abstract methods for interacting with a database through JPA

	public Optional<User> findByUsername(String username);
	
	@Transactional // Include this annotation if you want this to be part of a transaction
	@Modifying // Include this annotation if you intend to manipulate data, such as with INSERT, UPDATE, or DELETE
	@Query(value = "FROM User WHERE email LIKE %:substr%") // Use this to write custom SQL or HQL
	public List<User> findByEmailContains(String substr);
}
