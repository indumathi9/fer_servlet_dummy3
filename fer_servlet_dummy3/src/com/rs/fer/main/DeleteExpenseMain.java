package com.rs.fer.main;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class DeleteExpenseMain {

	public static void main(String[] args) {

		int expenseid = 7;

		FERService ferService = new FERServiceImpl();

		boolean deleteExpense = ferService.deleteExpense(expenseid);
	

		if (deleteExpense) {
			System.out.println("Expense deleted successfully");
		} else {
			System.out.println("Expense delete failed");
		}

	}

}
