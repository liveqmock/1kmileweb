package com.yeahwell.epu.web.interceptor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.tools.ant.DirectoryScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.ScalarStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.resolver.Resolver;

import com.yeahwell.epu.common.exceptions.ServiceUnavailableException;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;

public class i18nInterceptor extends HandlerInterceptorAdapter{
	
	private Logger logger = LoggerFactory.getLogger(i18nInterceptor.class);
	
	@Autowired
	private ServletContext servletContext;

	public static class I18NContainer {
		long lastUpdate;
		String name;
		String filepath;
		Map<String, Object> data = new HashMap<String, Object>();
		public static Yaml instanceOfYaml() {
			final DumperOptions options = new DumperOptions();
			options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
			options.setDefaultScalarStyle(ScalarStyle.DOUBLE_QUOTED);
			final Yaml yaml = new Yaml(options);
			yaml.addImplicitResolver(Tag.BOOL, Resolver.BOOL, "yYnNtTfFoO");
			yaml.addImplicitResolver(Tag.MERGE, Resolver.MERGE, "<");
			yaml.addImplicitResolver(Tag.NULL, Resolver.NULL, "~nN\0");
			yaml.addImplicitResolver(Tag.NULL, Resolver.EMPTY, null);
			yaml.addImplicitResolver(Tag.TIMESTAMP, Resolver.TIMESTAMP,
					"0123456789");
			yaml.addImplicitResolver(Tag.VALUE, Resolver.VALUE, "=");
			return yaml;
		}
		public Object get(final String name) {
			return null == data ? null : data.get(name);
		}
		public static I18NContainer load(final String name, final String filepath)
				throws IOException {
			final I18NContainer result = new I18NContainer();
			result.filepath = filepath;
			result.name = name;
			result.lastUpdate = 0;
			return result;
		}
		@SuppressWarnings("unchecked")
		protected void load() throws IOException {
			synchronized (this.data){
				this.data = (Map<String, Object>) instanceOfYaml().load(
						new FileInputStream(this.filepath));
			}
		}
	}

	public class I18N {
		public long cacheMilliSeconds = 0;
		Map<String, String> files = new HashMap<String, String>();
		Map<String, I18NContainer> cached = new HashMap<String, I18NContainer>();
		private String[] resolveNames(final String name) {
			final Locale locale = LocaleContextHolder.getLocale();
			//TODO perhaps store these too into a cache
			return new String[] {
					name + "_" + locale.getLanguage() + "_"
							+ locale.getCountry(),
							name + "_" + locale.getLanguage(),
							name + "_" + locale.getCountry(), name };
		}

		public I18NContainer get(final String name) {
			// resolve possible names
			final String[] resolveNames = resolveNames(name);
			String filepath = null, filename=null;
			for (final String string : resolveNames) {
				if ( files.containsKey(string) ) {
					filepath = files.get(string);
					filename = string;
					break;
				}
			}
			if (null == filepath) {
				return null;
			}
			I18NContainer container = null;
			if ( cached.containsKey(filename)) {
				container = cached.get(filename);
				if ( (Calendar.getInstance().getTimeInMillis() + this.cacheMilliSeconds) < container.lastUpdate ) {
					try {
						container.load();
					} catch (final IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new ServiceUnavailableException();
					}
				}
			} else {
				try {
					container = I18NContainer.load(filename, filepath);
					container.load();
				} catch (final IOException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
					throw new ServiceUnavailableException();
				}
			}
			return container;
		}

		public void add(final String filename, final String file) {
			files.put(filename, file);
		}
	}

	private final I18N i18n = new I18N();

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		if (null != modelAndView) {
			modelAndView.getModelMap().addAttribute("i18n", i18n);
			final BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
			final TemplateHashModel staticModels = wrapper.getStaticModels();
			final TemplateHashModel dateUtil = (TemplateHashModel) staticModels.get("com.yeahwell.epu.common.util.DateUtil");
			modelAndView.getModelMap().addAttribute("DateUtil", dateUtil);
			final TemplateHashModel applicationContext = (TemplateHashModel) staticModels.get("com.yeahwell.epu.web.context.ApplicationContextConfig");
			modelAndView.getModelMap().addAttribute("ApplicationContextConfig", applicationContext);
		}
		super.postHandle(request, response, handler, modelAndView);
	}

	public void setI18NFolder(final String i18NFolder) throws IOException {
		final DirectoryScanner scanner = new DirectoryScanner();
		scanner.setBasedir(servletContext.getRealPath(i18NFolder));
		scanner.setIncludes(new String[] { "*.yml" });
		scanner.scan();
		for (final String file : scanner.getIncludedFiles()) {
			final String filepath = scanner.getBasedir() + "/" + file;
			String filename = FilenameUtils.getName(filepath);
			filename = filename.substring(0, filename.length() - 4);
			i18n.add(filename.toLowerCase(), filepath);
		}
	}
}
