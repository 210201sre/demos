package com.revature.nonaccessmods;

public interface Calculable {

	// abstract methods do not have a body
	public default double area() {
		return 0;
	}
	
	// default methods do not have the abstract keyword
	public default void myMethod() {
		System.out.println("This is my default method!");
	}
	
	// static methods do not have the abstract keyword
	public static void myStaticMethod() {
		System.out.println("This is my static method!");
	}
}
