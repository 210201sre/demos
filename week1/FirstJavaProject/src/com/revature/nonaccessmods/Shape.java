package com.revature.nonaccessmods;

public abstract class Shape {

	public abstract double area();
	// abstract methods do not have an implementation
	
	// concrete methods are still allowed
	public Object test() {
		return new Object();
	}
	
	public int test2() {
		return 0;
	}
	
	public static void method() {
		System.out.println("Shape static method");
	}
	
	// Constructors are of course still allowed
	// They will be invoked indirectly though child classes
	public Shape() {
		super();
	}
}
