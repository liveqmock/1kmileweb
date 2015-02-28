package com.yeahwell.epu.web.interceptor;


import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yeahwell.epu.admin.model.SessionUser;
import com.yeahwell.epu.common.constants.Constants;
import com.yeahwell.epu.web.annotations.IfNeedLogin;

public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
	
	private String returnMerchantLoginURL;
	
	private String returnAdminLoginURL;

	@Override
	public void postHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		if (request.getMethod().equals(RequestMethod.HEAD)) {
			return;
		}
		final SessionUser sessionUser = (SessionUser) request.getSession()
				.getAttribute(Constants.SESSION_USER);
		// prevent caching so they cannot view the page after logout
		if (null != sessionUser) {
			response.setHeader("Cache-Control",
					"no-cache,no-store,must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
		}
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler)
			throws Exception {
		//获取注解,先获取MethodType,再获取BeanType,以MethodType为准
		IfNeedLogin ifNeedLogin = ((HandlerMethod) handler).getMethod()
				.getAnnotation(IfNeedLogin.class);
		if (null == ifNeedLogin) {
			ifNeedLogin = ((HandlerMethod) handler).getBeanType()
					.getAnnotation(IfNeedLogin.class);
		}
		// 默认需要登陆
		if ((null == ifNeedLogin) || ifNeedLogin.needLogin()) {
			final SessionUser sessionUser = (SessionUser) request.getSession()
					.getAttribute(Constants.SESSION_USER);
			if (null != sessionUser) {
				return true;
			} else {
				String returnURL = request.getScheme() + "://";
				returnURL += request.getHeader("host");
				returnURL += request.getRequestURI();
				if (null != request.getQueryString()) {
					returnURL += "?" + request.getQueryString();
				}
				if (returnURL.contains("admin")) {
					response.sendRedirect(request.getContextPath()
							+ returnAdminLoginURL + "?returnURL="
							+ URLEncoder.encode(returnURL, "UTF-8"));
				} else {
					response.sendRedirect(request.getContextPath()
							+ returnMerchantLoginURL + "?returnURL="
							+ URLEncoder.encode(returnURL, "UTF-8"));
				}
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public void afterCompletion(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(
			final HttpServletRequest request,
			final HttpServletResponse response, final Object handler)
			throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	public String getReturnMerchantLoginURL() {
		return returnMerchantLoginURL;
	}

	public void setReturnMerchantLoginURL(String returnMerchantLoginURL) {
		this.returnMerchantLoginURL = returnMerchantLoginURL;
	}

	public String getReturnAdminLoginURL() {
		return returnAdminLoginURL;
	}

	public void setReturnAdminLoginURL(String returnAdminLoginURL) {
		this.returnAdminLoginURL = returnAdminLoginURL;
	}

}
