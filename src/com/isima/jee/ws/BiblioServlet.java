package com.isima.jee.ws;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPMessage;

@SuppressWarnings("serial")
public class BiblioServlet extends HttpServlet {
	static MessageFactory messageFactory;
	static SoapManager soap;
	static InputStream is;

	static {
		try {
			messageFactory = MessageFactory.newInstance();
			soap = new SoapManager();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// resp.setContentType("text/html");
		String url = req.getRequestURI().replaceAll("/biblio", "");
		getServletContext().getRequestDispatcher("/WEB-INF" + url).forward(req,
				resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			MimeHeaders headers = getHeaders(req);
			InputStream is = req.getInputStream();
			SOAPMessage soapRequest = messageFactory.createMessage(headers, is);
			
			SOAPMessage soapResponse = soap.handleSOAPRequest(soapRequest);
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("text/xml;charset=\"utf-8\"");
			OutputStream os = resp.getOutputStream();
			soapResponse.writeTo(os);
			os.flush();
		} catch (Exception e) {
			System.out.println("message error  : " + e.getMessage());
		}

	}

	static MimeHeaders getHeaders(HttpServletRequest req) {
		Enumeration headerNames = req.getHeaderNames();
		MimeHeaders headers = new MimeHeaders();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			String headerValue = req.getHeader(headerName);
			StringTokenizer values = new StringTokenizer(headerValue, ",");
			while (values.hasMoreTokens()) {
				headers.addHeader(headerName, values.nextToken().trim());
			}
		}
		return headers;
	}
}
