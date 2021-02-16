package com.revature;

// The first line of a Java program is the package declaration
// You can have 1 package declaration per file

import com.revature.models.Employee;
import com.revature.models.Person;
import com.revature.models.Zoo;
import com.revature.utility.Math;

public class HelloWorld {

	public static void main(String[] args) {
		// This double slash is a single line "comment" which will be ignored by the Compiler
		/*
		 * You can also have multi-line comments that use slash star and star slash to begin and end
		 */

		System.out.println("Hello World!");
		
		// Control Flow
		// Allows us to modify the direction of instructions
		
		int myVariable = 5;
		// Syntax to declare a local variable
		// Java is Strongly and Statically typed
		// So we declare the type along with the variable
		
		if(myVariable < 10) {
			System.out.println("TRUE");
		} else if(myVariable < 5) {
			System.out.println("FALSE");
		} else if(myVariable < 2) {
			
		} else {
			
		}
		
		for(int i = 0; i < 10; i++) {
			System.out.println(i);
		}
		
		// continue to loop until this condition is false
		while(myVariable > 0) {
			
			System.out.println(myVariable);
			
			myVariable--;
			// Decrease the value by 1
		}
		
		String value = "TEST";
		// Strings, bytes, shorts, ints, chars, Enums
		switch(value) {
		case "TEST":
			// The break keyword will end the current loop or switch statement
			break;
		case "TEST2":
			break;
		default:
			break;
		}
		
		
		// Enhanced For loops operate in a similar fashion to for loops
		
		// We perform on a Collection or an array
		// arrays are effectively just lists of values
		for(String arg : args) {
			// This will loop through the array, and each individual String
			// will be stored in 'arg' one at a time
			
			System.out.println(arg);
		}
		
		// Operators are actions that we can perform, such as comparison of 2 values
		// Or incrementing or decrementing values
		// Or assignment
		
		// Logical Operators and Relational Operators
		
		boolean value2 = false;
		
		System.out.println(!value2); // ! is the NOT operator
		
		System.out.println(value2 && true); // && is the AND operator
		
		System.out.println(value2 || false); // || is the OR operator
		
		// The above 2 operators are called short-circuiting operators
		// And there is a version, called bitwise operators
		
		System.out.println(value2 & true); // && is the bitwise AND operator
		
		System.out.println(value2 | false); // || is the bitwise OR operator
		
		// 1111
		// 0011
		// Bitwise OR
		// 1111 -> 15
		// Bitwise AND
		// 0011 -> 3
		
		System.out.println(15 & 3);
		
		// We can compare many different values with relational operators
		
		System.out.println(5 < 3);
		
		System.out.println(7 == 14 / 2);
		
		System.out.println(5 >= 5);
		
		// Of the lesser known mathematical operators, is the MODULO or MODULUS operator, %
		
		System.out.println(7 % 3); // Modulo prints out the remainder left after integer division
		
		int z = Math.add(5, 7);
		
		System.out.println(z);
		
		Zoo zoo = new Zoo();
		
		zoo.addEmployee(new Employee());
		
		System.out.println(zoo.getWorkers());
		
		for(Employee emp : zoo.getWorkers()) {
			System.out.println(emp);
		}
		
//		zoo.setArea(-33);
		
		
		System.out.println(new Integer(5) == new Integer(5));
		System.out.println(Integer.valueOf(5) == Integer.valueOf(5));
		
		Person p = new Employee();
		
		Object o = p; // Up-Casting
		// Moving our reference variable UP the inheritance tree
		
		Employee e = (Employee) o; // Down-Casting
		// Moving our reference variable DOWN the inheritance tree
		// Potentially dangerous
		
//		Zoo z2 = (Zoo) o;
		
		// The instanceof operator will return true if the object IS-A instance of the Class
		// Then the down-cast would be safe
		if(o instanceof Zoo) {
			Zoo z2 = (Zoo) o;
		}
	}
}
