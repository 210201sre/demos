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
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER, mappedBy = "addresses")
	@JsonBackReference
	private Set<User> owners;
}
