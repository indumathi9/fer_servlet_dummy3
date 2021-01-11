package com.rs.fer.main;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class AddExpenseMain {

	public static void main(String[] args) {

		// 1. To get the input

		Expense expense = new Expense();

		expense.setType("Movie");
		expense.setDate("14/11/2020");
		expense.setPrice(250);
		expense.setNumOfItems(2);
		expense.setTotal(500);
		expense.setByWhom("sai");
		expense.setUserId(2);

		// 2: TO CALL THE SERVICE FOR BUSSINESS EXECUTION

		FERService ferService = new FERServiceImpl();

		boolean addExpense = ferService.addExpense(expense);

		// 3: TO PRINT THE STATUS

		if (addExpense) {
			System.out.println("Expense added successfully");
		} else {
			System.out.println("Expense add failed");

		}

	}

}
