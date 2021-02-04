package com.revature.exceptions;

import java.io.IOException;

public class Driver {

	public static void main(String[] args) {
		try {
			methodThree();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			methodFour();
		} catch(Throwable t) {
			t.printStackTrace();
			// The only way to stop the finally block from starting is to use
			// System.exit(0);
		} finally {
//			methodFour();
			System.out.println("Can you stop this from happening?");
		}
		
		
		System.out.println("My program is still going!");
	}

	public static void methodOne() throws IOException {
		throw new IOException();
	}
	
	public static void methodTwo() throws ClassNotFoundException {

	}
	
	public static void methodThree() throws ClassNotFoundException, IOException {
		methodOne();
		methodTwo();
	}
	
	public static void methodFour() {
		throw new NegativeValueException("Something was negative, when it wasn't supposed to be!", new OutOfMemoryError("No memory left!"));
	}
}
