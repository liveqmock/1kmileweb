package com.yeahwell.epu.web.util.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertiesPersister;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.ScalarStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.resolver.Resolver;

public class YamlPropertiesPersister implements PropertiesPersister {
	
	private static Logger logger = LoggerFactory
			.getLogger(YamlPropertiesPersister.class);
	
	@Override
	public void load(final Properties props, final InputStream is) throws IOException {
		load(props, new InputStreamReader(is));
	}

	/**
	 * We want to traverse map representing Yaml object and each time we will
	 * find String:String value pair we want to save it as Property. As we are
	 * going deeper into map we generate a compound key as path-like String
	 * 
	 * @param props
	 * @param reader
	 * @throws IOException
	 * @see org.springframework.util.PropertiesPersister#load(java.util.Properties,
	 *      java.io.Reader)
	 */
	@Override
	public void load(final Properties props, final Reader reader) throws IOException {
		final Yaml yaml = instanceOfYaml();
		@SuppressWarnings("unchecked")
		final
		Map<String, Object> load = (Map<String, Object>) yaml.load(reader);
		final Map<String, Object> map = load;
		// now we can populate supplied props
		assignProperties(props, map, null);
	}

	/**
	 * @param props
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public void assignProperties(final Properties props,
			final Map<String, Object> map, final String path) {
		logger.debug("Loading " + path);
		for (final Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			if (StringUtils.isNotEmpty(path)) {
				key = path + "." + key;
			}
			final Object val = entry.getValue();
			if (val instanceof String) {
				// see if we need to create a compound key
				props.put(key, val);
			} else if (val instanceof Map) {
				assignProperties(props, (Map<String, Object>) val, key);
			}
		}
	}

	@Override
	public void store(final Properties props, final OutputStream os,
			final String header) {
		//do nothing
	}

	@Override
	public void store(final Properties props, final Writer writer,
			final String header) throws IOException {
		//do nothing
	}

	@Override
	public void loadFromXml(final Properties props, final InputStream is)
			throws IOException {
		//do nothing
	}

	@Override
	public void storeToXml(final Properties props, final OutputStream os,
			final String header) throws IOException {
		//do nothing
	}

	@Override
	public void storeToXml(final Properties props, final OutputStream os,
			final String header, final String encoding) throws IOException {
		//do nothing
	}

	public static Yaml instanceOfYaml() {
		final DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setDefaultScalarStyle(ScalarStyle.DOUBLE_QUOTED);
		final Yaml yaml = new Yaml(options);
		yaml.addImplicitResolver(Tag.BOOL, Resolver.BOOL, "yYnNtTfFoO");
		yaml.addImplicitResolver(Tag.MERGE, Resolver.MERGE, "<");
		yaml.addImplicitResolver(Tag.NULL, Resolver.NULL, "~nN\0");
		yaml.addImplicitResolver(Tag.NULL, Resolver.EMPTY, null);
		yaml.addImplicitResolver(Tag.TIMESTAMP, Resolver.TIMESTAMP,"0123456789");
		yaml.addImplicitResolver(Tag.VALUE, Resolver.VALUE, "=");
		return yaml;
	}
}