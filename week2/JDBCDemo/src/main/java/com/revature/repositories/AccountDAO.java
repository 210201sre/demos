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

import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class AccountDAO implements IAccountDAO {
	
	private static final Logger log = LoggerFactory.getLogger(AccountDAO.class);

	@Override
	public List<Account> findAll() {
		List<Account> allAccounts = new ArrayList<>();
		// Start off with an empty list of Accounts
		
		// Use a try-with-resources block to obtain our Connection
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			// We populate and fill the allUsers list
			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT * FROM project0.accounts";
			// String to represent the SQL Query
			
			ResultSet rs = stmt.executeQuery(sql); // Send the Statement to the DB
			
			// Iterate through the response, one row at a time
			while(rs.next()) {
				// For each row, we grab the data from each column
				
				int id = rs.getInt("id");
				double balance = rs.getDouble("balance");
				
				Account a = new Account(id, balance);
				
				allAccounts.add(a);
			}
			
		} catch(SQLException e) {
			log.error("We failed to retrieve all accounts", e);
//			return null;
			// Likely to result in NullPointerExceptions
			// Potentially not the best decision
			return new ArrayList<>(); // Return an empty list
		}
		
		return allAccounts;
	}

	@Override
	public Account findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findByOwner(int userId) {
		List<Account> ownedAccounts = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT project0.accounts.id, project0.accounts.balance FROM "
					+ "project0.accounts INNER JOIN project0.users_accounts_jt ON project0.accounts.id = project0.users_accounts_jt.account"
					+ " WHERE project0.users_accounts_jt.owner = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				double balance = rs.getDouble("balance");
				
				Account a = new Account(id, balance);
				// This data could potentially be a duplicate
				// Since there could be multiple owners for the same account
				
				if(!ownedAccounts.contains(a)) {
					ownedAccounts.add(a);
				}
			}
			
		} catch(SQLException e) {
			log.error("We failed to retrieve accounts owned by User with ID " + userId, e);
			// Likely to result in NullPointerExceptions
			// Potentially not the best decision
			return new ArrayList<>(); // Return an empty list
		}
		
		return ownedAccounts;
	}

	@Override
	public int insert(Account a) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
