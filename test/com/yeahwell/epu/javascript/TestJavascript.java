package com.yeahwell.epu.javascript;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class TestJavascript {

	@Test
	public void testCallJavascriptInJava() {

		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = sem.getEngineByName("javascript");
		try {
			// 调用直接JAVASCRIPT语句
			se.eval("println('111');");
			String tmpstr = "test string";
			se.eval(("println('" + tmpstr + "');"));

			// 调用无参数方法JAVASCRIPT函数
			se.eval("function sayHello() {"
					+ "  print('Hello '+strname+'!');return 'my name is '+strname;"
					+ "}");
			Invocable invocableEngine = (Invocable) se;
			se.put("strname", "testname");
			String callbackvalue = (String) invocableEngine
					.invokeFunction("sayHello");
			System.out.println(callbackvalue);

			// 调用有参数JAVASCRIPT函数
			se.eval("function sayHello2(strname2) {"
					+ "  print('Hello '+strname+'!');return 'my name is '+strname2;"
					+ "}");
			callbackvalue = (String) invocableEngine.invokeFunction(
					"sayHello2", "testname2");
			System.out.println(callbackvalue);

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCallJavaInJavascript() {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = null;
		se = sem.getEngineByExtension("js");
		// se=sem.getEngineByMimeType("text/javascript");
		// se=sem.getEngineByName("javascript");
		String jsscript = "function mytest(){"
				+ "println(ContextProperties);"
				+ "ContextProperties.remove('three');"
				+ "println(ContextProperties);"
				+ "println('1:'+ContextProperties.containsKey('one'));"
				+ "println('2:'+ContextProperties.containsKey('four'));"
				+ "println('size:'+ContextProperties.size());"
				+ "println(ContextProperties.get('one'));"
				+ "return (ContextProperties.get('one')>ContextProperties.get('two'))?true:false;}";// 也可以调用绑定对象的别的方法，如自定义对象的方法等
		Bindings bindings = se.getBindings(ScriptContext.ENGINE_SCOPE);
		Map map = new HashMap();
		map.put("one", 1);
		map.put("two", 5);
		map.put("three", 5);
		// bindings.putAll(map);
		// System.out.println("one:"+bindings.get("one"));
		// System.out.println("two:"+bindings.get("two"));

		// 绑定Map对象，也可以绑定自定义的Java对象
		bindings.put("ContextProperties", map);
		try {
			Object flag = null;
			se.eval(jsscript, bindings);
			if (se instanceof Invocable) {
				Invocable invoke = (Invocable) se;
				flag = invoke.invokeFunction("mytest");
				System.out.println("flag:" + flag);
			}
			// 输出经过JavaScript修改后的map对象
			System.out.println("map:" + map);

		} catch (NoSuchMethodException ex) {
			Logger.getLogger(TestJavascript.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (ScriptException ex) {
			Logger.getLogger(TestJavascript.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}

}