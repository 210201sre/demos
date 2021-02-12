package com.revature;

import java.util.Objects;

public class Employee extends Person {

	private String department;
	private int employeeId;
	
	public Employee() {
		super();
	}
	
	public Employee(int age, String name) {
		super(age, name);
	}

	public Employee(int age, String name, String department, int employeeId) {
		this(age, name);
		this.department = department;
		this.employeeId = employeeId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(department, employeeId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		return Objects.equals(department, other.department) && employeeId == other.employeeId;
	}

	@Override
	public String toString() {
		return "Employee [department=" + department + ", employeeId=" + employeeId + "]";
	}
}
