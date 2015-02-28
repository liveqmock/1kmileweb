package com.yeahwell.epu.common.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XSSUtil {

	private static Logger logger = LoggerFactory.getLogger(XSSUtil.class);

	private XSSUtil() {
	}

	public static String escapeHtml4(String value){
		return StringEscapeUtils.escapeHtml4(value);
	}
	
	public static String[] escapeHtml4(String[] values){
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
	
	public static String clean(String value){
		return Jsoup.clean(value, Whitelist.basic());
	}
	
	public static String[] clean(String[] values){
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

    public static void main(String[] args) {
	}
}
