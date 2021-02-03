package com.revature.scanner;

import java.util.Scanner;

public class Driver {
	
	/*
	 * Scanner is a class in Java that can be used to scan through many different things
	 * 
	 * For example, you can scan through files, console, or any InputStream
	 * 
	 * You can parse inputs for different types, you can read entire lines, or partial lines, etc
	 * 
	 * Note: Recommend to only create 1 Scanner per InputStream (such as System.in) as Scanner
	 * can sometimes behave oddly
	 * 
	 * The only native way to determine if a Scanner is still open is by invoking some methods, and checking to
	 * see if it throws an IllegalStateException
	 */

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Please enter some user input:");
		
		String input = s.nextLine(); // Reads entire line of input
		
		String[] words = input.split(" ");
		
		for(String word : words) {
			System.out.println(word);
		}
		
		System.out.println("Please enter an integer:");
		
		int x = s.nextInt(); // Prompt the user to enter an integer
		// Only reading part of the system input
		
		System.out.println("You entered: " + x);
		
		System.out.println(s.nextLine()); // This grabbed the leftover
		// content in the System InputStream buffer without prompting for more input
		
		// My recommendation, if you do want to use the nextInt or similar methods is to do the following
		int y;
		try {
			y = Integer.parseInt(s.nextLine().split(" ")[0]);
		} catch(NumberFormatException e) {
			System.out.println("You did not enter a valid integer");
			y = 0; // Set the value to a default result
		}
		// This will read the entire line, and only grab the first word, discarding the rest
		// The first word is then attempted to be parsed into an int by the Integer.parseInt method
		
		System.out.println(y);
		
		int z = s.nextInt();
		s.nextLine(); // Discard the rest
		
		s.close();
	}
}
