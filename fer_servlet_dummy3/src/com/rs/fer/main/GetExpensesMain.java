package com.rs.fer.main;

import java.util.List;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class GetExpensesMain {

	public static void main(String[] args) {

		int userId = 1;

		FERService ferService = new FERServiceImpl();

		List<Expense> expenses = ferService.getExpenses(userId);

		if (expenses == null || expenses.isEmpty()) {
			System.out.println("No expenses were found");
		} else {
			for (Expense expense : expenses) {
				System.out.println("ID:" + expense.getId());
				System.out.println("Type:" + expense.getType());
				System.out.println("Date:" + expense.getDate());
				System.out.println("Price:" + expense.getPrice());
				System.out.println("Number Of Items:" + expense.getNumOfItems());
				System.out.println("Total:" + expense.getTotal());
				System.out.println("Bywhom:" + expense.getByWhom());
				System.out.println("UserId:" + expense.getUserId());
				System.out.println("-------------------------------------------------");
			}
		}

	}

}
