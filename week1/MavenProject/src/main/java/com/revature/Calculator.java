package com.revature;

public class Calculator {

	public int add(int... arr) {
		int sum = 0;
		
		for(int value : arr) {
			int original = sum;
			sum += value;
			
			if(value > 0 && sum <= original) {
				// Something went wrong
				throw new IllegalArgumentException("Integer Overflow Occurred");
			}
			
			if(value < 0 && sum >= original) {
				throw new IllegalArgumentException("Integer Underflow Occurred");
			}
		}
		
		return sum;
	}

	public double multiply(double x, double y) {
		return x * y;
	}
	
	// Example method that is very difficult to test
	public void myMethod() {
		System.out.println("Print something");
		
		// Let's pretend this is supposed to verify that a user is authenticated
		
		// This could be one of your expected behaviors
		if(/* Not authenticated */ true) {
			throw new RuntimeException("Not authenticated");
		}
	}
}
