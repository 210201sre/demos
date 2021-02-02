package com.revature.models;

import java.util.Objects;

public class Employee extends Person {

	private String name;
	private int age;
	private String dept;
	
	public Employee() {
		super();
	}

	public Employee(String name, int age, String dept) {
		super();
		this.name = name;
		this.age = age;
		this.dept = dept;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, dept, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		return age == other.age && Objects.equals(dept, other.dept) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", dept=" + dept + "]";
	}
	
	@Override
	public void speak() {
		System.out.println("Hello, my name is " + name);
	}
}
