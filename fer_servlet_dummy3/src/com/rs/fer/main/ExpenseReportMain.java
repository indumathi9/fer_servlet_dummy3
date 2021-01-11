package com.rs.fer.main;

import java.util.List;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class ExpenseReportMain {

	public static void main(String[] args) {

		int userId = 4;
		String Type = "entertainment";
		String fromDate = "01/11/2020";
		String toDate = "31/11/2020";

		FERService ferService = new FERServiceImpl();

		List<Expense> expenseReport = ferService.expenseReport(userId,Type,fromDate,toDate);

		if (expenseReport == null || expenseReport.isEmpty()) {
			System.out.println("No expense report was found");
		} else {
			for (Expense expense : expenseReport) {
				System.out.println("ID:" + expense.getId());
				System.out.println("Type:" + expense.getType());
				System.out.println("Date:" + expense.getDate());
				System.out.println("Price:" + expense.getPrice());
				System.out.println("Number Of Items:" + expense.getNumOfItems());
				System.out.println("Total:" + expense.getTotal());
				System.out.println("Bywhom:" + expense.getByWhom());
				// System.out.println("UserId:" + expense.getUserId());
				System.out.println("-------------------------------------------------");
			}
		}

	}
}