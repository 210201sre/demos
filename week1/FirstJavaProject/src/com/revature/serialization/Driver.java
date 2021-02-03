package com.revature.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Driver {
	
	private List<Pet> petDB = new ArrayList<>();

	public static void main(String[] args) {
		
		Driver d = new Driver();
		
//		d.petDB.add(new Pet("Fluffy", 8, "Cerberus", "Hagrid", 5, Gender.MALE));
//		
//		d.serialize();
		
		d.deserialize();
		
		for(Pet p : d.petDB) {
			System.out.println(p);
		}
	}
	
	// Convert from Java -> File
	public void serialize() {
		
		// This is called a try-with-resources block
		// It will allow you to instantiate an object that implements the AutoClosable interface
		// This will cause the object to automatically be closed at the end of the block
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("files/pet.db"))) {
			
			oos.writeObject(this.petDB);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// Convert from File -> Java
	public void deserialize() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("files/pet.db"))) {
			
			this.petDB = (List<Pet>) ois.readObject();
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
