package com.yeahwell.epu.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Escape all html tag and " charactors when getting parameter from http request<br>
 * Using StringEscapeUtils.escapeHtml4()
 * 
 * @author yeahwell
 * 
 */
public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {
	
	public XSSHttpServletRequestWrapper(HttpServletRequest servletRequest) {
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
			encodedValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
		}
		return encodedValues;
	}

	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return StringEscapeUtils.escapeHtml4(value);
	}
}