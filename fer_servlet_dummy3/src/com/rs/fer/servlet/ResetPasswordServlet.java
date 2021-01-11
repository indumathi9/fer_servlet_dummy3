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

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.LayoutUtil;

@WebServlet("/resetPassword")

public class ResetPasswordServlet extends HttpServlet {

	FERService ferservice = null;

	@Override
	public void init(ServletConfig config) throws ServletException {

		ferservice = new FERServiceImpl();
		super.init(config);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		PrintWriter out = resp.getWriter();

		LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, session.getAttribute("username"));

		String currentPassword = req.getParameter("currentPassword");

		String newPassword = req.getParameter("newPassword");

		boolean resetpassword = ferservice.resetPassword(userId, currentPassword, newPassword);

		if (resetpassword) {
			out.println("USER PASSWORD CHANGED SUCCESSFULLY");

		} else {
			out.println("USER PASSWORD CHANGE FAILED");

		}

		LayoutUtil.displayFooter(req, resp);
	}

	@Override
	public void destroy() {
		ferservice = null;
		super.destroy();
	}

}
