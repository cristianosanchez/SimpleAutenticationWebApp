package br.com.demo.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.debug("filter beginning");
		chain.doFilter(request, response);
		logger.debug("filter ending");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		logger.debug("filter initing");
	}

	public void destroy() {
		logger.debug("filter destroying");
	}
}
