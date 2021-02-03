package com.revature.wrapperclasses;

import com.revature.models.Employee;

public class Driver {

	// In Java, there are 8 types that are NOT objects, which are
	// the primitive types
	// Everything else is an Object
	// Java created Object representations of these 8 primitives
	
	// int -> Integer
	// char -> Character
	// byte -> Byte
	// short -> Short
	// long -> Long
	// boolean -> Boolean
	// float -> Float
	// double -> Double
	
	// Each of these are called Wrapper classes for their corresponding
	// primitive type
	
	// Wrapper classes have a feature called "autoboxing" and
	// "autounboxing", where these types can be automatically
	// converted back and forth as needed
	
	/*
	 * Another characteristic about Wrapper classes is that they are
	 * immutable. Which means you cannot change the value.
	 * 
	 * This has an impact on how you use these Wrapper classes
	 * across multiple methods.
	 */
	
	public static void main(String[] args) {
		
		// The 2 parameters will be auto-unboxed into primitive ints
		// for the method call
//		int result = add(new Integer(3), new Integer(5));
		int result = add(Integer.valueOf(3), Integer.valueOf(5));
		// After the  method returns an Integer Object,
		// it will be auto-unboxed back into a primitive int to store
		// in the variable
		
		Employee e = new Employee("Sue", 25, "Marketing");
		changeEmployee(e);
		
		System.out.println(e);
		
		Integer value = Integer.valueOf(4);
		
		value = changeInteger(value);
		
		System.out.println(value);
	}

	public static Integer add(int x, int y) {
		// The result of the addition will be autoboxed into
		// an Integer Object before it is returned
		return x + y;
	}
	 
	public static void changeEmployee(Employee e) {
		e.setAge(0);
		e.setName("Larry");
	}
	
	public static Integer changeInteger(Integer i) {
		return i.valueOf(i + 1);
	}
}
