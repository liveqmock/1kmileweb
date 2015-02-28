package com.yeahwell.epu.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yeahwell
 */
public class LoadParamListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory
			.getLogger(LoadParamListener.class);

	/**
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(final ServletContextEvent servletContextEvent) {
	}

	/**
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(final ServletContextEvent servletContextEvent) {
		servletContextEvent.getServletContext().setAttribute("contextPath",
				servletContextEvent.getServletContext().getContextPath());
	}
}
