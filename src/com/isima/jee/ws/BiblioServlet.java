package com.isima.jee.ws;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class BiblioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		BiblioService bs = new BiblioService();
		resp.setContentType("text/plain");
		bs.deleteBook(1);
		resp.getWriter().println("Hello");
//		int bn = bs.addBook(1, "Chi 7aja", 8.9, "qlqchose");
//		resp.getWriter().println("Book Num = " + bn);
	}
}
