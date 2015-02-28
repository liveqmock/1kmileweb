package com.yeahwell.epu.web.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yeahwell.epu.web.log.LogType;
import com.yeahwell.epu.web.util.SerialNumberBuilder;

public class EnterInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory
			.getLogger(LogType.EPU_COMMON.val);

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String journalSeqNo = SerialNumberBuilder.buildJournalSeqNo();
		request.setAttribute("serial_number", journalSeqNo);
		logger.info("流水[{}] begin path {}", journalSeqNo,
				request.getServletPath());
		ServletContext servletContext = request.getSession()
				.getServletContext();
		servletContext.setAttribute("staticFileRoot", request.getContextPath() + "/static");
		servletContext.setAttribute("staticFileVer", "20140516");
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("流水[{}] end path {}",
				request.getAttribute("serial_number"), request.getServletPath());
	}
}