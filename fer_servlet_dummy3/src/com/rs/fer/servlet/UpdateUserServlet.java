package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.bean.User;
import com.rs.fer.servce.Impl.FERServiceImpl;
import com.rs.fer.service.FERService;
import com.rs.fer.util.LayoutUtil;

@WebServlet("/displayUpdateUser")

public class UpdateUserServlet extends HttpServlet {
	FERService ferService = new FERServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("user");
		boolean isUpdateUser = ferService.updateUser(user);
		PrintWriter out = resp.getWriter();
		LayoutUtil.displayHEaderAndLeftFrame(req, resp, out, session.getAttribute("username"));

		if (isUpdateUser) {
			out.println("user updated sucessfully ");

		} else {
			out.println("user updated failed ");

		}

		LayoutUtil.displayFooter(req, resp);
	}

}
