package br.com.demo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.demo.encoding.Base64;

public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("executing get method");
		
		String base64Message = request.getParameter("m");
		logger.debug("query string ["+base64Message+"]");
		
		String originalMessage = Base64.decode(base64Message);
		
		request.setAttribute("base64Message", base64Message);
		request.setAttribute("message", originalMessage);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/show-message.jsp");
		dispatcher.forward(request, response);
	}
}
