package com.revature.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Zoo {

	private Employee[] workers; // default value of "null" which represents a lack of an object
	private List<Animal> animals;
	private double area;
	private String name;
	private String address;
	private boolean open;
	
	// We have a concept of Constructors
	// These are special methods whose job it is to initialize all of the properties for the object
	
	// Java provides a "default constructor" if you do not create ANY constructor yourself
	
	// access-modifier class-name(parameters, if any)
	public Zoo() {
		super();
		// Declare an array of size zero
		// Note: Arrays are fixed-length
		// Once created, they cannot change sizes
		this.workers = new Employee[0];
		this.animals = new ArrayList<>();
		this.area = 0;
		this.name = "";
		this.address = "";
		this.open = false;
	}
	
	public Zoo(Employee[] workers, List<Animal> animals, double area, String name, String address, boolean open) {
		super();
		this.workers = workers;
		this.animals = animals;
		this.area = area;
		this.name = name;
		this.address = address;
		this.open = open;
	}

	public Zoo(double area, String name, String address, boolean open) {
		super();
		this.area = area;
		this.name = name;
		this.address = address;
		this.open = open;
		this.workers = new Employee[0];
		this.animals = new ArrayList<>();
	}

	public void addEmployee(Employee e) {
		Employee[] arr = new Employee[this.workers.length + 1];
		// Allocate memory for a larger array
		
		// Copy the contents from the old array into the new array
		for(int i = 0; i < this.workers.length; i++) {
			arr[i] = this.workers[i];
		}
		
		// Add the new Employee to the end of the new array
		arr[this.workers.length] = e;
		
		//Redirect the workers reference variable to point to the larger array
		this.workers = arr;
	}
	
	public void addAnimal(Animal a) {
		animals.add(a);
	}

	public Employee[] getWorkers() {
		return workers;
	}

	public void setWorkers(Employee[] workers) {
		this.workers = workers;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		if(area < 0) {
			throw new RuntimeException("Area cannot be negative!");
			// Instead of assigning a negative a value, the program will crash
		}
		
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(workers);
		result = prime * result + Objects.hash(address, animals, area, name, open);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Zoo)) {
			return false;
		}
		Zoo other = (Zoo) obj;
		return Objects.equals(address, other.address) && Objects.equals(animals, other.animals)
				&& Double.doubleToLongBits(area) == Double.doubleToLongBits(other.area)
				&& Objects.equals(name, other.name) && open == other.open && Arrays.equals(workers, other.workers);
	}

	@Override
	public String toString() {
		return "Zoo [workers=" + Arrays.toString(workers) + ", animals=" + animals + ", area=" + area + ", name=" + name
				+ ", address=" + address + ", open=" + open + "]";
	}
}
