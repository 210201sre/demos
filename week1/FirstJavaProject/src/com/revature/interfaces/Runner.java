package com.revature.interfaces;

// Interfaces are a contract that define a set of behavior for any class/object that implements this contract
// We define a set of methods in an interface, that do not have a method body/implementation
// They are simply labels are "contracts"
// The details on how this function will execute must be defined by the class that implements this
// contract/interface

public interface Runner {

	// all methods in an interface (that do not use the "default" or "static" keywords) are automatically
	// public abstract functions
	public void run();
	
	// The "default" keyword was added in for interfaces to provide default methods in the case of backwards
	// compatibility
	public default void run2() {
		System.out.println("The run2 method was invoked");
	}
	
	// All fields in interfaces are public static and final
	public static final int myVariable = 5;
}
