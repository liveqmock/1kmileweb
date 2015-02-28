package com.yeahwell.epu.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * Escape all html tag and " charactors when getting parameter from http request<br>
 * Using Jsoup.clean(unsafe, Whitelist.basic());
 * 
 * @author yeahwell
 * 
 */
public class XSSHttpServletRequestWrapperJsoup extends HttpServletRequestWrapper {
	
	public XSSHttpServletRequestWrapperJsoup(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = Jsoup.clean(values[i], Whitelist.basic());
		}
		return encodedValues;
	}

	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return Jsoup.clean(value, Whitelist.basic());
	}
}