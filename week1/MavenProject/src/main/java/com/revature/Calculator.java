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
}
