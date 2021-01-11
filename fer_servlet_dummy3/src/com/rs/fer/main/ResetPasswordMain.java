package com.rs.fer.main;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class ResetPasswordMain {

	public static void main(String[] args) {

		int id = 4;
		String currentPassword = "nwpsw";
		String newPassword = "Ab@123";

		FERService ferService = new FERServiceImpl();

		boolean resetPassword = ferService.resetPassword(id, currentPassword, newPassword);

		if (resetPassword) {
			System.out.println("password reset successfully");
		} else {
			System.out.println("password  reset failed");

		}

	}

}
