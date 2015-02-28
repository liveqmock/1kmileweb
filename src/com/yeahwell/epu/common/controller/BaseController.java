package com.yeahwell.epu.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;

import com.yeahwell.epu.admin.model.SessionUser;
import com.yeahwell.epu.common.constants.Constants;

public class BaseController implements Constants{
	
	private final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	private HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public SessionUser getSessionCustomer() {
		return (SessionUser) request.getSession().getAttribute(SESSION_USER);
	}
	
	/**
	 * 登陆
	 * @param customer
	 */
	public void loginSession(final SessionUser sessionUser) {
//		Cookie cookie = null;
//		cookie = new Cookie(cookieName, java.net.URLEncoder.encode(cookieValue, "utf-8"));
//        cookie.setPath("/");
//        cookie.setDomain(".1kmile.com");
		request.getSession().setAttribute(SESSION_USER, sessionUser);
	}
	
	/**
	 * 清除session
	 */
	public void logoutSession() {
		request.getSession().removeAttribute(SESSION_USER);
	}
	
	public void setSessionVal(final String name, final Object value) {
		request.getSession().setAttribute(name, value);
	}

	public Object getSessionVal(final String name) {
		return request.getSession().getAttribute(name);
	}
	
	public String getRequestURL(){
		String returnURL = request.getScheme() + "://";
		returnURL += request.getHeader("host");
		returnURL += request.getContextPath();
		return returnURL;
	}
	
	/**
	 * 得到当前设备的信息
	 * @return
	 */
	public Device getCurrentDevice() {
		return DeviceUtils.getCurrentDevice(request);
	}

}
