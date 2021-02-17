package com.revature.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Must be included at the top of the class to be managed by Hibernate/JPA
@Table(name = "users", schema = "springdata")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {

	@Id // Must be included on the Primary Key Field
	@Column(name = "user_id", nullable = false, unique = true, updatable = false)
	// Hibernate can perform DDL statements, which will create tables with the above constraints
	// However, Hibernate does not always have to perform DDL, this can be disabled
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Length(min = 1)
	private String firstName;
	private String lastName;
	
	@Length(min = 5)
	@NotBlank
	@Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*")
	// Specifying that the first letter cannot be a number, and only alpha-numeric characters are allowed
	// No spaces, for example
	private String username;
	
	@NotEmpty
	private String password;

	@Email
	private String email;
	
	// @JsonManagedReference along with @JsonBackReference will inform Jackson-Databind about bi-directional relationships
	// So that we can properly create JSON for these objects without falling into an infinite loop
//	@JsonManagedReference("label1")
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	// Lazy Fetching will return a proxy object in place of the actual data
	// When the data is actually needed, Hibernate/JPA will then actually go and fetch the data from the DB
	// This is useful in some scenarios because, we may not actually need the data across this relationship
	// If that is so, then we can save some performance, by not wasting time grabbing unneeded data
	// However, if your Hibernate Session has closed, we would be unable to then grab the data at a later time
	// This can cause some exceptions to be thrown
	// The other side of the coin, is that if we know that we will need the data, it is overall faster to grab\
	// all of the data at once, instead of making 2 queries
	private Set<Address> addresses;
}
