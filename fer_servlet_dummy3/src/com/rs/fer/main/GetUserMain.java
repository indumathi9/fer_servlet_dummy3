package com.rs.fer.main;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class GetUserMain {

	public static void main(String[] args) {

		int userId = 1;

		FERService ferService = new FERServiceImpl();

		User user = ferService.getUser(userId);

		if (user == null) {
			System.out.println("No user found");
		} else {
			System.out.println("FirstNAme:" + user.getFirstName());
			System.out.println("MiddleName:" + user.getMiddleName());
			System.out.println("LastName:" + user.getLastName());
			System.out.println("----------------------------------------");
			System.out.println("Email:" + user.getEmail());
			System.out.println("Mobile:" + user.getMobile());
			System.out.println("----------------------------------------");
			System.out.println("Line1:" + user.getAddress().getLineOne());
			System.out.println("Line2:" + user.getAddress().getLineTwo());
			System.out.println("City:" + user.getAddress().getCity());
			System.out.println("State:" + user.getAddress().getState());
			System.out.println("Pincode:" + user.getAddress().getPinCode());
			System.out.println("Country:" + user.getAddress().getCountry());
			System.out.println("----------------------------------------");

		}

	}
}
