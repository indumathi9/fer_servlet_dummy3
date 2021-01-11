package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

@WebServlet("/expenseReport")
public class ExpenseReportServlet extends HttpServlet {

	FERService ferService = new FERServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session.getAttribute("username"));

		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		String expenseType = request.getParameter("expenseType");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");

		List<Expense> expenses = ferService.expenseReport(userId, expenseType, fromDate, toDate);

		if (expenses == null || expenses.isEmpty()) {
			out.println("expense not found for your search criteria");
		} else {

			out.println("<table border='1' align='center'>");
			out.println(" <tr>");
			out.println("    <td width='106px'>Expense Type</td>");
			out.println("    <td width='106px'>Date</td>");
			out.println("    <td width='106px'>Price</td>");
			out.println("    <td width='106px'>Number Of Items</td>");
			out.println("    <td width='106px'>Total</td>");
			out.println("    <td width='106px'>Bywhom</td>");

			out.println(" </tr>");
			for (Expense expense : expenses) {
				out.println(" <tr>");
				out.println("    <td width='106px'>" + expense.getType() + "</td>");
				out.println("    <td width='106px'>" + expense.getDate() + "</td>");
				out.println("    <td width='106px'>" + expense.getPrice() + "</td>");
				out.println("    <td width='106px'>" + expense.getNumberOfItems() + "</td>");
				out.println("    <td width='106px'>" + expense.getType() + "</td>");
				out.println("    <td width='106px'>" + expense.getByWhom() + "</td>");

				out.println(" </tr>");
			}

			out.println("</table>");
		}

		LayoutUtil.displayFooter(request, response);

	}

}
