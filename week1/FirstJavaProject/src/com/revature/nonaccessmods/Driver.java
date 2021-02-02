package com.revature.nonaccessmods;

public class Driver {
	
	/*
	 * There are a fair few number of non-access modifiers in Java:
	 * 
	 * The first keyword is static:
	 * 		This keyword declares a method or variable to belong to the class itself
	 * 		We say that it will have STATIC SCOPE
	 * 		If you are familiar with other languages, static scope is not quite the same as a "Global" scope, but it is similar
	 * 		Static properties will exist for the entire lifetime/runtime of a Java application
	 * 		Note: this keyword cannot be used on local variables
	 * 
	 * final:
	 * 		This keyword prevents something from being changed
	 * 		For variables: the value cannot be reassigned
	 * 		For methods: The method cannot be overridden
	 * 		For classes: The class cannot be extended
	 * 
	 * abstract keyword:
	 * 		This applies to methods and classes
	 * 		This keyword can only be used on methods if they are inside an interface or an abstract class
	 * 		Abstract classes cannot be instantiated (the same as interfaces)
	 * 		Conflicts with the final keyword, since a class must be extended in order to implement the abstract methods
	 * 			The idea is that abstract classes should be extended, and final prevents that, so together they would
	 * 			leave you with a useless class
	 *		Exclusively used for inheritance
	 *		Abstract methods do NOT have implementations
	 *		Abstract classes may still have regular methods
	 *
	 * synchronized keyword:
	 * 		Can only be used on variables and methods
	 * 		It prevents the variable/method from being accessed by multiple threads at once
	 * 
	 * volatile keyword:
	 * 		This applies to variables (only static and instance scope variables)
	 * 		Changes the location that the variable is stored to main memory	(NOT the heap or the stack)
	 * 		Since each Thread will get its own Stack, they cannot access anything in the stack from another Thread
	 * 		The volatile keyword would make this variable accessible by all threads
	 * 
	 * transient keyword:
	 * 		Applies to variables (only static and instance scope)
	 * 		Prevents the variable from being serialized
	 * 
	 * strictfp keyword:
	 * 		This applies to classes
	 * 		This forces all methods and fields within the class to follow strict IEEE floating point format
	 * 
	 * native keyword:
	 * 		This applies to methods
	 * 		This means that the implementation for t he method will be provided
	 * 			somewhere else in a different language
	 * 
	 */

	// In any method, we can replace an array syntax with triple dots
	// This feature is called var-args (variable arguments)
	public static void main(String... args) {
		Circle c = new Circle();
		c.setRadius(10);
		
		System.out.println(c.area());
		
		c.myMethod();
		
		// Static methods from classes can be invoked through the object directly, unlike static methods from interfaces
		// But it is not a recommended technique
		c.method();
		
		Shape.method();
		
		// Static methods from an interface must be invoked through the interface name
		// They are not inherited by the subclass
		Calculable.myStaticMethod();
		
//		System.out.println(add());
		
		System.out.println(add(1, 2));
		
		System.out.println(add(1, 2, 3, 4, 5, 6));
		
		int[] arr = new int[] {1, 2, 3, 4, 5};
		// Syntax used to initialize the contents of an array when you create it
		
		System.out.println(add(0, 0, arr));
	}
	
	
	public static int add(int x, int y) {
		return x + y;
	}
	
	public static int add(int x, int y, int z) {
		return x + y + z;
	}
	
	// Some restrictions:
	// You may only have a single var-args parameter per method
	// The var-args parameter must be the last parameter
	public static int add(int x, int y, int... arr) {
		int sum = x + y;
		
		for(int value : arr) {
			sum += value;
		}
		
		return sum;
	}
}
