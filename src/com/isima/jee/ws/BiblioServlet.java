package com.isima.jee.ws;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class BiblioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		BiblioService bs = new BiblioService();
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello");
		int n = bs.addAuthor("Amine", "Ben hammou", "Aubiere");
		int bn = bs.addBook(n, "Chi 7aja", 8.9, "qlqchose");
		resp.getWriter().println("Num = " + n);
		resp.getWriter().println("Book Num = " + bn);
	}
}
