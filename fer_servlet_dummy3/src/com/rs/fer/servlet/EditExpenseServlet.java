package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.LayoutUtil;

@WebServlet("/editExpense")
public class EditExpenseServlet extends HttpServlet {
	FERService ferService = null;

	@Override

	public void init(ServletConfig config) throws ServletException {
		ferService = new FERServiceImpl();
		super.init(config);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Expense expense = new Expense();
		
		
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
		
		
		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));

		

		expense.setType(req.getParameter("expensetype"));
		expense.setDate(req.getParameter("date"));
		expense.setPrice(Float.parseFloat(req.getParameter("price")));
		expense.setNumberOfItems(Integer.parseInt(req.getParameter("no of items")));
		expense.setTotal(Float.parseFloat(req.getParameter("total")));
		expense.setBywhom(req.getParameter("bywhom"));
		expense.setId(Integer.parseInt(session.getAttribute("expenseId").toString()));

		boolean editExpense = ferService.editExpense(expense);

		if (editExpense) {
			out.println("Expense edited successfully");

		} else {
			out.println("Expense edition failed");

		}
		LayoutUtil.displayFooter(req, resp);

	}

	@Override
	public void destroy() {
		ferService = null;
		super.destroy();
	}
}
