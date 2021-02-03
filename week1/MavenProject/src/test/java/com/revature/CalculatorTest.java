package com.revature;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTest {
	
	private Calculator calculator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("BeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("AfterClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Before");
		
		calculator = new Calculator();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After");
		
		calculator = null;
	}

	@Test
	public void testCommutativeProperty() {
		System.out.println("Test 1");
		
		Random r = new Random();
		
		int x = r.nextInt(Integer.MAX_VALUE / 2);
		int y = r.nextInt(Integer.MAX_VALUE / 2);
		
		assertTrue(calculator.add(x, y) == calculator.add(y, x));
	}
	
	@Test
	public void testInverseProperty() {
		System.out.println("Test 2");
		
		Random r = new Random();
		
		int x = r.nextInt(Integer.MAX_VALUE);
		
		assertTrue(calculator.add(x, -1 * x) == 0);
	}

	@Test
	public void testAddAgain() {
		System.out.println("Test 3");
		
		assertThrows(IllegalArgumentException.class,
				() -> calculator.add(Integer.MAX_VALUE, 1)
		);
	}
	
	@Test
	public void testMultiplyInverseProperty() {
		System.out.println("Test 4");
		
		Random r = new Random();
		
		double x = r.nextDouble() * Integer.MAX_VALUE;
		
		double inverse = 1.0d / x;
		
		assertTrue(calculator.multiply(x, inverse) == 1.0d);
	}
}
