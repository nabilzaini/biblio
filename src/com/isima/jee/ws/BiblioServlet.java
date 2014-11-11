package com.isima.jee.ws;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class BiblioServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		BiblioService bs = new BiblioService();
		resp.setContentType("text/html");
		resp.getWriter().println("<h1> Hello World ! </h1>");
	}
	
}
