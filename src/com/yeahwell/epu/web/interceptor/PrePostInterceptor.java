package com.yeahwell.epu.web.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yeahwell.epu.web.annotations.PostHandler;
import com.yeahwell.epu.web.annotations.PreHandler;

public class PrePostInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory
			.getLogger(PrePostInterceptor.class);

	@Override
	public void postHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		final HandlerMethod hm = (HandlerMethod) handler;
		final Method[] methods = ((HandlerMethod) handler).getBeanType()
				.getMethods();
		Method m = null;
		PostHandler anno = null;
		for (final Method method : methods) {
			if (null != (anno = method.getAnnotation(PostHandler.class))) {
				m = method;
				break;
			}
		}
		try {
			boolean call = true;
			if (null != m) {
				if (null != anno.ignore()) {
					call = !Arrays.asList(anno.ignore()).contains(
							hm.getMethod().getName());
				}
				if (call) {
					m.invoke(hm.getBean(), request, response, handler,
							modelAndView);
				}
			}
		} catch (final InvocationTargetException e) {
			logger.error("Failed to invoke target: " + e.getMessage());
		}
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler)
			throws Exception {
		final HandlerMethod hm = (HandlerMethod) handler;
		final Method[] methods = ((HandlerMethod) handler).getBeanType()
				.getMethods();
		Method m = null;
		PreHandler anno = null;
		for (final Method method : methods) {
			if (null != (anno = method.getAnnotation(PreHandler.class))) {
				m = method;
				break;
			}
		}
		boolean call = true;
		if (null != m) {
			if (null != anno.ignore()) {
				call = !Arrays.asList(anno.ignore()).contains(
						hm.getMethod().getName());
			}
			if (call) {
				return (Boolean) m.invoke(hm.getBean(), request, response,
						handler);
			}
		}
		return true;
	}
}
