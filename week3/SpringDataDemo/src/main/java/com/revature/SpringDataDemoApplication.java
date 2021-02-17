package com.revature;

import java.util.Collections;
import java.util.HashSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.models.Address;
import com.revature.models.User;

@SpringBootApplication
public class SpringDataDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataDemoApplication.class, args);
		
//		User u = new User(0, "Matthew", "Oberlies", "moberlies", "password", "matthew.oberlies@revature.com", new HashSet<>());
//		
//		System.out.println(u);
//		
//		Address a = new Address(1, "", "", "", "Herndon", new HashSet<>());
//		a.getOwners().add(u);
//		
//		u.getAddresses().add(a);
//		
//		System.out.println(u);
	}
}
