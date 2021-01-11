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
import com.rs.fer.service.impl.FERserviceImpl;
import com.rs.fer.util.LayoutUtil;

@WebServlet("/addexpense")

public class AddExpenseServlet  extends HttpServlet{
	FERService ferservice = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		 ferservice =new FERserviceImpl();
		
		super.init(config);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		int userId=Integer.parseInt(session.getAttribute("userId").toString());
		
		Expense expense = new Expense();

		expense.setType(req.getParameter("expensetype"));
		expense.setDate(req.getParameter("date"));
		expense.setPrice(Float.parseFloat(req.getParameter("price")));
		expense.setNumberOfItems(Integer.parseInt(req.getParameter("noofitems")));
		expense.setTotal(Float.parseFloat(req.getParameter("total")));
		expense.setByWhom(req.getParameter("bywhom"));
		expense.setUserId(userId);

		boolean addExpense = ferservice.addExpense(expense);
		
		PrintWriter out = resp.getWriter();
		
		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));

		if (addExpense) {
			out.println("expense added successfully");
			
		} else {
		 out.println("expense added failed");
		}
		LayoutUtil.displayFooter(req, resp);
		
	}

	@Override
	public void destroy() {
		ferservice = null;
		super.destroy();
	}
}
