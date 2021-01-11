package com.rs.fer.main;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class GetExpenseMain {

	public static void main(String[] args) {

		int expenseId = 11;
		FERService ferService = new FERServiceImpl();

		Expense expense = ferService.getExpense(expenseId);

		if (expense == null) {
			System.out.println("Expense not found");
		} else {
			System.out.println("ID:" + expense.getId());
			System.out.println("Type:" + expense.getType());
			System.out.println("Date:" + expense.getDate());
			System.out.println("Price:" + expense.getPrice());
			System.out.println("Number Of Items:" + expense.getNumOfItems());
			System.out.println("Total:" + expense.getTotal());
			System.out.println("ByWhom:" + expense.getByWhom());
			System.out.println("UserId:" + expense.getUserId());
			System.out.println("-------------------------------------------------");
		}

	}
}
