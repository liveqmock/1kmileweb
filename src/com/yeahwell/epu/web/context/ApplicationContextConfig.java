package com.yeahwell.epu.web.context;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextConfig
{
  private static Map<String, String> configMap = new HashMap<String, String>();

  public static String get(String key) {
    return (String)configMap.get(key);
  }

  public static synchronized void put(String key, String value) {
    configMap.put(key, value);
  }

  public static synchronized void remove(String key) {
    configMap.remove(key);
  }
}