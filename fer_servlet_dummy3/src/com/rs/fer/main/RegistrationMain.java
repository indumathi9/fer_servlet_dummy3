package com.rs.fer.main;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class RegistrationMain {

	public static void main(String[] args) {
		
		

		// 1: TO GET THE INPUT
		User user = new User();

		user.setFirstName("abc");
		user.setMiddleName("");
		user.setLastName("ln");
		user.setEmail("ns@user.com");
		user.setUsername("ABCDE");
		user.setPassword("Abcd");
		user.setMobile("8444934646");

		// 2: TO CALL THE SERVICE FOR BUSSINESS EXECUTION

		FERService ferService = new FERServiceImpl();

		boolean isRegister = ferService.registration(user);

		// 3: TO PRINT THE STATUS

		if (isRegister) {
			System.out.println("User registered successfully");
		} else {
			System.out.println("User registration failed");

		}

	}

}
