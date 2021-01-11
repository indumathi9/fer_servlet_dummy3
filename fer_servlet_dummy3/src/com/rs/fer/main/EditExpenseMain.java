package com.rs.fer.main;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class EditExpenseMain {

	public static void main(String[] args) {

		// 1. To get input

		Expense expense = new Expense();

		expense.setType("TravelLog");
		expense.setDate("14/11/2020");
		expense.setPrice(250);
		expense.setNumOfItems(2);
		expense.setTotal(500);
		expense.setByWhom("sai");
		expense.setId(15);

		// 2: TO CALL THE SERVICE FOR BUSSINESS EXECUTION

		FERService ferService = new FERServiceImpl();

		boolean editExpense = ferService.editExpense(expense);

		// 3: TO PRINT THE STATUS

		if (editExpense) {
			System.out.println("Expense edited successfully");
		} else {
			System.out.println("Expense edit failed");

		}

	}

}
