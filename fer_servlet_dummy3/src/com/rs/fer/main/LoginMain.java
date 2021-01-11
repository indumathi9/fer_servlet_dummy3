package com.rs.fer.main;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class LoginMain {

	public static void main(String[] args) {

		// 1. To get the input

		String username = "sandy7272";
		String password = "sandy89";

		// 2. To call the service for bussiness execution

		FERService ferService = new FERServiceImpl();
		int userId = ferService.login(username, password);
		// 3. to print the status

		if (userId > 0) {
			System.out.println("login is success");
		} else {
			System.out.println("login is failed");
		}

	}

}
