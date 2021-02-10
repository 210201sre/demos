package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * The Connection utility class that is designed to obtain a Connection to the database
 * is often structured as a "Singleton"
 * 
 * Singleton is a Design Pattern for classes where they are restricted to only ever be
 * instantiated at most once.
 * 
 * This particular example, is not a perfect Singleton, since we are creating a Singleton
 * around the built in Connection interface from the JDBC API
 * 
 * Since we don't own the Connection interface, we are using this utility class
 * to wrap the Connection interface into a Singleton Design
 * 
 * 
 * Characteristics:
 * 
 * Private Constructors
 * Static field of an instance of this class (or whatever should be a Singleton)
 * Leverage a public static getInstance() method
 */
public class ConnectionUtil {

	private static Connection conn = null;
	
	private static final Logger log = LoggerFactory.getLogger(ConnectionUtil.class);
	
	private ConnectionUtil() {
		super();
	}
	
	public static Connection getConnection() {
		try {
			if(conn != null && !conn.isClosed()) {
				return conn;
			}
		} catch (SQLException e) {
			log.error("We failed to reuse a Connection!", e);
			return null;
			// By returning null, there is a High chance that we will get a NullPointerException in other
			// parts of the codebase
		}
		
		// If the above if statement is false, then we should instead return a new Connection
		
		// jdbc:postgresql://endpoint:port_number/db_name
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USERNAME");
		String password = System.getenv("DB_PASSWORD");
		// The above statements are reading information from our environment variables
		
		// Important Note: When you change/modify your environment variables, you MUST restart STS
		// STS "snapshots" the state of the environment variables when it launches
		// Most applications work in this way
		
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			log.error("We failed to establish a Connection!", e);
			return null;
		}
		
		return conn;
	}
}
