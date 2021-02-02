package com.revature.accessmods;

public class Driver {
	
	/*
	 * In Java, there are 4 "Access Modifiers", which are keywords that define a field/method's visibility:
	 * 
	 * public:
	 * 		Visible for the entire project/everything
	 * 
	 * private:
	 * 		Visible only within the class where the method/field is declared
	 * 
	 * default:
	 * 		Note: default access is a lack of a keyword as an access modifier
	 * 		Visible within the package in which the field/method was declared
	 * 		AKA package-private
	 * 
	 * protected:
	 * 		The same as default, except also visible within child classes
	 */

	public int x;
	protected int y;
	int z;
	private int p;
	
	public static void main(String[] args) {
		Driver d = new Driver();
		
		System.out.println(d.p); // We do have access to private fields within this class
	}
}
