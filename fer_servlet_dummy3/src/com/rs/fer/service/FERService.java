package com.rs.fer.service;

import java.util.List;

import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;

public interface FERService {

	boolean registration(User user);

	int login(String username, String password);

	boolean addExpense(Expense expense);

	boolean editExpense(Expense expense);

	boolean deleteExpense(int expenseid);

	boolean resetPassword(int userId, String currentPassword, String newPassword);

	Expense getExpense(int expenseId);
	
	User  getUser(int userId);
	
	boolean updateUser(User user);
	
	List<Expense> getExpenses(int userId);
	
	List<Expense> expenseReport(int userId, String Type,String fromDate,String toDate);
}
