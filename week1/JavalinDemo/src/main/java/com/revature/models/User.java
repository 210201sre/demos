package com.revature.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class User implements Serializable {

	private static final long serialVersionUID = -5417184248983340117L;
	
	private int id;
	private String firstName;
	private String lastName;
	private Set<String> trustedIPs;
	private String username;
	private String password;
	private UserRole role;
	private String email;
	
	public User() {
		super();
	}

	public User(String username, String password, UserRole role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public User(int id, String firstName, String lastName, Set<String> trustedIPs, String username, String password,
			UserRole role, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.trustedIPs = trustedIPs;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<String> getTrustedIPs() {
		return trustedIPs;
	}

	public void setTrustedIPs(Set<String> trustedIPs) {
		this.trustedIPs = trustedIPs;
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password, role, trustedIPs, username);
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
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& role == other.role && Objects.equals(trustedIPs, other.trustedIPs)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", trustedIPs=" + trustedIPs
				+ ", username=" + username + ", password=" + password + ", role=" + role + ", email=" + email + "]";
	}
}
