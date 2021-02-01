package com.revature.memory;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		List<Object> objects = new ArrayList<>();

		int i = 0;
		// This is an infinite loop
		// It will never end
		
		try {
			
			while(true) {
				// Allocate more memory
				// It's a tiny bit of memory
				objects.add(new Object());
				// Going to hold on to the reference
				// The Garbage Collector will not be able to take it away
				i++;
				
	  			System.out.println(i + " objects created so far");
				
	  			objects.remove(0);
			}
		
		} catch(Error e) {
			System.out.println(i + " objects created before crashing");
		}
	}
}
