package com.revature.strings;

public class Driver {

	public static void main(String[] args) {
		// Unlike every other class in Java, we can instantiate an instance of the String class
		// through the use of String literals

		String s = "Hello World!";
		// What exactly is going on here?
		
		String s2 = "Hello World!";
		
		// Strings have their own special region of the Heap, known as the "String Pool"
		// The 2 Strings above are inside the String Pool
		// Although there is really only 1 String object, and both s and s2 point to that same String
		
		// When you use a String literal, the String object will be created in the String Pool at the beginning
		// of the runtime of the application
		
		// Each of these String literals, are now effectively just reference variables to the corresponding
		// String object in the String Pool
		
		String s3 = new String("Hello World!");
		// Outside the String Pool
		
		// We can of course still use the String Constructor like on line 24, which will create Strings outside the
		// String Pool, but still inside the Heap.
		
		// Strings are immutable
		
		String s4 = "Hello " + "World!";
		// Is s4 pointing to a String in the String Pool or not?
		
		System.out.println(s4.equals(s)); // Are the contents the same?
		System.out.println(s4 == s); // Are they the same object in memory?
		
		String s5 = "Hello ";
		
		String s6 = s5 + "World!";
		
		System.out.println(s6.equals(s)); // Are the contents the same?
		System.out.println(s6 == s); // Are they the same object in memory?
		
		// The + operator for Strings is smart, to an extent
		// If both operands are String literals
		// Then the result will also be in the String Pool
		
		// However, if one of the operands is NOT a String literal
		// The result will be a String Object outside the String Pool
		
		// The intern method will either find or construct a String inside the String Pool
		// that has the same contents, and returns a reference to it
		String s7 = s6.intern();
		
		System.out.println(s7 == s);
		
		// Collectively, all of the methods on the String class are called the String API
		// There are many methods available to interact with Strings in different ways
		System.out.println(s7.compareTo(s5));
		// If s7 would lexicographically come before s5, then the result of the method will be
		// a negative value
		
		// If they are equal, the result is zero
		
		// Otherwise, the result is positive
		
		// Generally speaking, the magnitude of the positive/negative value should indicate
		// how far apart the Strings are lexicographically (approximately)
		
		// However, some implementations of compareTo, ignore this, and instead only return 1, 0, or -1
		
		s7 = s7.concat(" something");
		// All methods of the String API that produce new Strings (except for intern), will always produce Strings
		// that are outside the String Pool
		
		// You will be expected to be familiar with some of the more methods in the String API
		
		// substr
		// replace & replaceAll & replaceFirst
		// charAt & indexOf
		// split
		// length & isEmpty
		
		// While String is immutable, other classes may not be
		
		// The StringBuilder and StringBuffer classes are mutable versions of the String class
		
		StringBuilder sb = new StringBuilder("Hello");
		
		sb.append(" ");
		sb.append("World!");
		String result = sb.toString(); // Creates a String outside the String Pool
		
		System.out.println(result == s);
		
		// What is the difference between StringBuilder and StringBuffer?
		
		
		StringBuffer sb2 = new StringBuffer();
		
		// The difference is that StringBuffer is thread-safe, whereas StringBuilder is NOT
		// Other side is that Since StringBuilder does not use the synchronized keyword,
		// the methods are actually faster than in StringBuffer
		// There is no overhead for thread-safety
		
		// Generally speaking, in more modern days, it is recommended to always use StringBuilder
		
		// and if you need thread-safety, you should create your own
		// Use the synchronized keyword yourself to handle any potential data corruption
		
		// This way, you have the benefit of extra performance whenever you do not need to be
		// concerned about thread-safety
	}
}
