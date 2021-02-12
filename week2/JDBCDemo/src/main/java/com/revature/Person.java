package com.revature;

import java.util.Objects;

public class Person {

	private int age;
	private String name;
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Person)) {
			return false;
		}
		Person other = (Person) obj;
		return age == other.age && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
}
