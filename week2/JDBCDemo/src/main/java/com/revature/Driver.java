package com.revature;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// Person is a superclass of Employee
		
		// List<Person> is NOT a superclass of a List<Employee>

		List<? extends Person> myPersonList;
		
		List<Person> myPersonList2;
		
		List<Employee> myEmployeeList = new ArrayList<>();
		
		myEmployeeList.add(new Employee());
		
		myPersonList = myEmployeeList;
		// List<? extends Person> IS a superclass of List<Employee>
		
//		myPersonList2 = myEmployeeList;
		// The above statement does not work
		
		List<?> myObjectList = myEmployeeList;
		// Effectively just a List of Objects
		
//		Object o = myObjectList.get(0);
		
		
		// The entire Collections API
		// The Reflections API
		// Both really cool
	}
}
