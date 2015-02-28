package com.yeahwell.epu.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamedTree {

	Map<String, NamedTree> children = new HashMap<String, NamedTree>();
	Object value;

	private List<String> split(final String name) {
		return new ArrayList<String>(Arrays.asList(name.split("[.]")));
	}

	private void addToChildren(final List<String> name, final Object val) {
		if (0 == name.size()) {
			value = val;
		} else {
			final String first = name.remove(0);
			NamedTree child = null;
			child = children.get(first);
			if (null == child) {
				child = new NamedTree();
				children.put(first, child);
			}
			child.addToChildren(name, val);
		}
	}

	private Object getValueFromChildren(final List<String> tokens) {
		if (0 == tokens.size()) {
			return value;
		}
		final NamedTree child = children.get(tokens.remove(0));
		if (null == child) {
			return null;
		}
		return child.getValueFromChildren(tokens);
	}

	public void add(final String name, final Object val) {
		final List<String> tokens = split(name);
		addToChildren(tokens, val);
	}

	public Object get(final String name) {
		return getValue(name);
	}

	public Object getValue(final String name) {
		final List<String> tokens = split(name);
		final Object objValue = getValueFromChildren(tokens);
		return objValue;
	}
}
