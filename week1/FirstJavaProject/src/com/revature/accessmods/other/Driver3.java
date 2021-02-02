package com.revature.accessmods.other;

import com.revature.accessmods.Driver;

public class Driver3 extends Driver {

	public static void main(String[] args) {
		Driver driver = new Driver();
		
		Driver3 driver3 = new Driver3();

		// These two statements are invalid, because neither field is visible
//		System.out.println(driver.z);
//		System.out.println(driver.y);
		
		// However, the protected field is visible through our subclass
		// Even though we are in a different package
		System.out.println(driver3.y);
		
		// Always accessible/visible because it is public
		System.out.println(driver.x);
	}

}
