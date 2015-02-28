package com.yeahwell.epu.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeahwell.epu.web.log.LogType;

public class PropertiesUtil {
	private static Logger logger = LoggerFactory.getLogger(LogType.EPU_COMMON
			.name());

	private static final Map<String, String> cachedProp = new HashMap<String, String>();
	private static final String DEFAULT_CONF_FILE_LOCAL = "env.properties";

	static {
		loadFile();
		for (String key : cachedProp.keySet()) {
			System.setProperty(key, (String) cachedProp.get(key));
			logger.debug(key + "=" + (String) cachedProp.get(key));
		}
	}

	public static void loadFile() {
		Properties filePropIn = new Properties();
		InputStream input = null;
		Properties properties = new Properties();
		try {
			input = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(DEFAULT_CONF_FILE_LOCAL);
			filePropIn.load(input);
			logger.info("项目内置配置文件{}加载成功!", DEFAULT_CONF_FILE_LOCAL);
		} catch (FileNotFoundException e) {
			logger.warn("项目内置配置文件{}没有找到!", DEFAULT_CONF_FILE_LOCAL);
		} catch (IOException e) {
			logger.error(e.getMessage());
			logger.warn("项目内置配置文件{}加载失败!", DEFAULT_CONF_FILE_LOCAL);
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.warn("项目内置配置文件{}加载失败!", DEFAULT_CONF_FILE_LOCAL);
		} finally {
			if (input != null) {
				try {
					input.close();
					input = null;
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		properties.putAll(filePropIn);
		for (Iterator it = properties.keySet().iterator(); it.hasNext();) {
			Object key = it.next();
			cachedProp.put((String) key, (String) properties.get(key));
		}
	}

	public static String getValue(String key) {
		if (cachedProp.containsKey(key)) {
			return (String) cachedProp.get(key);
		}
		return null;
	}

}