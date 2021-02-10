package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAO implements IUserDAO {
	// We will follow a Data Access Object Design Pattern
	// This class will have instance methods
	// whose responsibility is to perform common CRUD operations
	// against a DB
	
	// Common operations:
	// findAll
	// findByUsername
	// findById
	// update
	// insert
	// delete
	
	// We will declare a method for each of the above operations
	// Because of this consistency in needed CRUD operations
	// it is not uncommon to create an interface for our DAO classes
	
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

	@Override
	public List<User> findAll() {
		List<User> allUsers = new ArrayList<>();
		// Start off with an empty list of Users
		
		// Use a try-with-resources block to obtain our Connection
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			// We populate and fill the allUsers list
			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM project0.users";
			// String to represent the SQL Query
			
			ResultSet rs = stmt.executeQuery(sql); // Send the Statement to the DB
			
			// Iterate through the response, one row at a time
			while(rs.next()) {
				// For each row, we grab the data from each column
				
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				// We have to do a bit of a roundabout technique to work with Enums, but it's not too bad
				// We first grab the Enum from the DB and interpret as a String
				// And then we take the String and interpet into the Java's Enum instance
				Role role = Role.valueOf(rs.getString("role"));
				
				User u = new User(id, username, password, role);
				
				allUsers.add(u);
			}
			
		} catch(SQLException e) {
			log.error("We failed to retrieve all users", e);
//			return null;
			// Likely to result in NullPointerExceptions
			// Potentially not the best decision
			return new ArrayList<>(); // Return an empty list
		}
		
		return allUsers;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(User u) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO project0.users (username, password, role) VALUES (?, ?, ?) RETURNING project0.users.id";
			// The RETURNING clause will return back some data in response to our statement
			
			// Instead of using conn.createStatement, we will use conn.prepareStatement
			
			// We protect ourselves from SQL Injection by leveraging PreparedStatements instead of regular Statements
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			// The positions of the '?'s begin at position 1
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			
			// To accommodate the differences between Java and SQL's Enums, we will use setObject
			stmt.setObject(3, u.getRole());
			
			ResultSet rs;
			
			if( (rs = stmt.executeQuery()) != null) {
				rs.next();
				// Again, ResultSets begin positioned BEFORE the first row
				
				int id = rs.getInt(1);
				
				return id;
			}
			
		} catch (SQLException e) {
			log.error("We failed to insert a new user", e);
			return -1;
		}
		
		// -1 will indicate that something wrong happened while trying to insert a User
		// as -1 is not a valid ID
		return -1;
		
		
		// Alternatively, you may throw a Custom Exception
	}

	@Override
	public boolean update(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
