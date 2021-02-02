package com.revature.initialization;

public class Driver {
	
	private int x;
	
	/*
	 * Java has an Order of Initialization
	 */
	
	// These independent curly braces are referred as "Initializers"
	
	// Instance Initializer
	
	{
		System.out.println("1st Instance Initializer");
	}
	
	{
		System.out.println("2nd Instance Initializer");
	}
	
	// Static Initializer
	// Occurs first
	// Specifically, it is executed when the class is loaded the first time by the JVM
	static {
		System.out.println("1st static initializer");
	}
	
	static {
		System.out.println("2nd static initializer");
	}
	
	// Constructors occur after the instance initializers
	public Driver() {
		super();
		System.out.println("No Argument Constructor");
	}
	
	public Driver(int x) {
		super();
		this.x = x;
		System.out.println("One Argument Constructor");
	}

	public static void main(String[] args) {
		new Driver();

		new Driver(5);
	}
}
