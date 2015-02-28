package com.yeahwell.epu.web.util.sitemesh;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.ConfigDecoratorMapper;

public class SitemeshCustomDecoratorMapper extends ConfigDecoratorMapper {

	private static Logger logger = LoggerFactory
			.getLogger(SitemeshCustomDecoratorMapper.class);

	@Override
	public void init(final Config config, final Properties properties,
			final DecoratorMapper parent) throws InstantiationException {
		super.init(config, properties, parent);
	}

	@Override
	public Decorator getDecorator(final HttpServletRequest request,
			final Page page) {
		String servletPath = request.getServletPath();
		logger.info(servletPath);
		
		//每个页面可指定自己的decorator
		if (page.isPropertySet("meta.decorator")) {
			final Decorator decorator = getNamedDecorator(request,
					page.getProperty("meta.decorator"));
			return decorator;
		}
		
		//商户未登陆
		if(servletPath.startsWith("/merchant/logout")
			|| servletPath.startsWith("/merchant/login")){
			final Decorator decorator = getNamedDecorator(request, "merchantUnlogged");
			logger.info(decorator.getPage());
			return decorator;
		}
		
		//管理员未登陆
		if(servletPath.startsWith("/admin/logout")
				|| servletPath.startsWith("/admin/login")){
			final Decorator decorator = getNamedDecorator(request, "adminUnlogged");
			logger.info(decorator.getPage());
			return decorator;
		}
		
		//商户已登陆
		if(servletPath.startsWith("/merchant")) {
			final Decorator decorator = getNamedDecorator(request, "merchantLoggedin");
			logger.info(decorator.getPage());
			return decorator;
		}
		
		//管理员已登陆
		if(servletPath.startsWith("/admin")){
			final Decorator decorator = getNamedDecorator(request, "adminLoggedin");
			logger.info(decorator.getPage());
			return decorator;
		}
		
		return super.getDecorator(request, page);
	}
	
}
