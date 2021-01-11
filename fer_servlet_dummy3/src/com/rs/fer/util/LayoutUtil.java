 package com.rs.fer.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LayoutUtil {

	public static void displayHeaderAndLeftFrame(HttpServletRequest req, HttpServletResponse resp, PrintWriter out,
			Object username) throws ServletException, IOException {

		req.getRequestDispatcher("Layout/Header.html").include(req, resp);

		out.println(username);

		req.getRequestDispatcher("Layout/LeftFrame.html").include(req, resp);
	}

	public static void displayFooter(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getRequestDispatcher("Layout/Footer.html").include(req, resp);
	}

}
