package com.yeahwell.epu.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XSSFilter implements Filter{

	FilterConfig filterConfig;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//chain.doFilter(new XSSHttpServletRequestWrapper((HttpServletRequest) request), response);
		chain.doFilter(new XSSHttpServletRequestWrapperJsoup((HttpServletRequest) request), response);
	}

	public void destroy() {
		this.filterConfig = null;
	}

}
