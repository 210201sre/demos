package com.revature.nonaccessmods;

public class Circle extends Shape implements Calculable {

	private double radius;
	
	// The abstract method is inherited from both the Calculable interface and the Shape class
	// These methods have the same method signature, so there is only 1 method to implement
	@Override
	public double area() {
		return Math.PI * radius * radius;
	}
	
	@Override
	public String test() {
		return "Test function overriden";
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	// The below attempt to override the test2 method is invalid
//	@Override
//	public Integer test2() {
//		return new Integer(5);
//	}
}
