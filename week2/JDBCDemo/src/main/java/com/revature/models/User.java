package com.revature.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

// Should have a field for every column in the table of the DB
// (for the most part, there are some occasional exceptions)

// This is actually the Java Bean Design Pattern
public class User implements Serializable {

	private static final long serialVersionUID = 7382850360365866259L;
	
	private int id;
	private String username;
	private String password;
	private Role role;
	private List<Account> accounts;
	
	public User() {
		super();
	}

	public User(int id, String username, String password, Role role, List<Account> accounts) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.accounts = accounts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public void addAccount(Account account) {
		this.accounts.add(account);
	}
	
	public boolean removeAccount(Account account) {
		return this.accounts.remove(account);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accounts, id, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(accounts, other.accounts) && id == other.id && Objects.equals(password, other.password)
				&& role == other.role && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", accounts="
				+ accounts + "]";
	}
}
