package com.revature.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(exclude = {"owners"}) @ToString(exclude = {"owners"}) // These 2 annotations are needed to prevent the
// recursive loop when printing an object with a bi-directional relationship
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String street;
	private String secondary;
	
	@Length(min = 2, max = 2)
	private String state;
	
	private String city;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER, mappedBy = "addresses")
	/*
	 * Hibernate (and any ORM) have different operations that are performed against objects.
	 * Mentioned that objects have 1 of 3 states:
	 * TRANSIENT: Have yet to be associated with the DB (newly created objects)
	 * PERSISTENT: An object is associated with the DB, and it is being synchronized (in both directions)
	 * 		Note: This means you have an active session with the DB
	 * 		When you modify the object, the ORM will update the record in the table
	 * DETACHED: The object was previously associated with the DB, but the session was closed
	 * 
	 * The above operations impact the object states listed above:
	 * 
	 * DETACH: Take a PERSISTENT entity and make it DETACHED (close the session)
	 * MERGE: Take a DETACHED entity and make it PERSISTENT (with a new session)
	 * PERSIST: Take a TRANSIENT entity and make it PERSISTENT
	 * REFRESH: Query the DB, and update the object with the information from the DB (works with PERSISTENT state)
	 * 		A bit uncommon
	 * REMOVE: DELETE the object in the DB, which will also move the object from PERSISTENT to DETACHED (might be TRANSIENT)
	 * 
	 * Cascading an operation across a mapping, is effectively asking: What should you do with this HAS-A relationship,
	 * 		when something happens to the entity?
	 * 
	 * In this case, what should we do to the addresses if something happens to the user?
	 */
	@JsonBackReference
	private Set<User> owners;
}
