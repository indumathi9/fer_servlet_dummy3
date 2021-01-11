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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {

		ferService = new FERServiceImpl();

		super.init(config);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		int userId = ferService.login(username, password);

		PrintWriter out = resp.getWriter();
		if (userId > 0) {

			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			session.setAttribute("userId", userId);

			LayoutUtil.displayHeaderAndLeftFrame(req, resp, out, username);

			out.println("Welcome to the user:" + username);

			LayoutUtil.displayFooter(req, resp);

			
		} else {
			out.println("login is failed");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}

	}

	@Override
	public void destroy() {
		ferService = null;

		super.destroy();
	}
}
