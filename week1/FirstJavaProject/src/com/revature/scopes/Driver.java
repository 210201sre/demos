package com.revature.scopes;

public class Driver {
	
	/*
	 * In Java, there are 4 variable scopes
	 * 
	 * Static/Class/*Global* Scope
	 * Instance Scope
	 * Method Scope
	 * Block Scope
	 * 		Block scopes can be nested however deep you wish
	 * 
	 * Variables are visible within their scope and further nested scopes
	 * 
	 * The term "Field" refers to static or instance scoped variables
	 * The term "local" variable refers to method or block scoped variables
	 * 
	 * Static and instance scoped variables are provided with an initial value automatically
	 * 
	 * Each primitive is given an initial value according to that specific primitive
	 * 
	 * Reference variables are given an initial value of "null"
	 * 
	 * Default values:
	 * int - 0
	 * short - 0
	 * long - 0
	 * byte - 0
	 * char - The special null character, which has the ascii value of zero
	 * boolean - false
	 * float - 0f
	 * double - 0.0
	 * 
	 */
	
	
	// Both static and instance scope variables are declared outside of any method
	// and inside the Class's curly braces
	
	// Static scoped variables are indicated with the static keyword
	// Instance scoped variables do not have any keyword
	
	public static int myVar = 15; // Static scope
	// Belong to the class itself
	
	public double value = 33.5; // Instance scope
	// Belongs to the instances of a class (the objects)
	
	public static Driver d;

	public static void main(String[] args) {
		// The parameter args is Method Scope
		
		// Some consider the variable x to be Block Scope, but others say Method Scope
		// The reasoning is that some say that it is block scope because it is within the
		// curly braces of the method itself, which designates a "block"
		// Whereas others, say that because the variable is not within any "nested" block
		// it is therefore visible for the entire method, and is Method Scope
		int x = 5;
		
		while(x > 0) {
			// the variable y is block scope
			int y = x * x;
			
			System.out.println(y);
			x--;
		}
		
//		System.out.println(y);
		// y is not visible here

		
		if(x == 0) {
			int y = 50;
		} else {
			// y is not visible here
			// since this is a different block scope
			// that is NOT nested
			int z = 33;
		}
	}
	
	public void otherMethod() {
		
//		Driver d;
		
		System.out.println(d);
		
		float f = 33.5f;
		
		short s1 = 5;
		
		short s2 = 10;
		
		short s3 = (short) (s1 + s2);
	}
}
