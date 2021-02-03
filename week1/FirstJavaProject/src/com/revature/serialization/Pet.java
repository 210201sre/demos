package com.revature.serialization;

import java.io.Serializable;
import java.util.Objects;

import com.revature.interfaces.Gender;

public class Pet implements Serializable, Comparable<Pet> {

	// This is a UID that is used to identify classes in the case of multiple classes
	// having the same name
	private static final long serialVersionUID = -9058755237575108287L;
	
	private String name;
	private int tagNumber;
	private String breed;
	private String ownerName;
	private int age;
	private Gender gender;
	
	public Pet() {
		super();
	}

	public Pet(String name, int tagNumber, String breed, String ownerName, int age, Gender gender) {
		super();
		this.name = name;
		this.tagNumber = tagNumber;
		this.breed = breed;
		this.ownerName = ownerName;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTagNumber() {
		return tagNumber;
	}

	public void setTagNumber(int tagNumber) {
		this.tagNumber = tagNumber;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, breed, gender, name, ownerName, tagNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Pet)) {
			return false;
		}
		Pet other = (Pet) obj;
		return age == other.age && Objects.equals(breed, other.breed) && gender == other.gender
				&& Objects.equals(name, other.name) && Objects.equals(ownerName, other.ownerName)
				&& tagNumber == other.tagNumber;
	}

	@Override
	public String toString() {
		return "Pet [name=" + name + ", tagNumber=" + tagNumber + ", breed=" + breed + ", ownerName=" + ownerName
				+ ", age=" + age + ", gender=" + gender + "]";
	}

	@Override
	public int compareTo(Pet otherPet) {
		if(this.tagNumber < otherPet.getTagNumber()) {
			return -1;
		} else if(this.tagNumber > otherPet.getTagNumber()) {
			return 1;
		}
		
		if(!this.ownerName.equals(otherPet.getOwnerName())) {
			return this.ownerName.compareTo(otherPet.getOwnerName());
		}
		
		// Continue to compare fields
		
		// If all fields were matching
		return 0;
	}
}
