package com.yeahwell.epu.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeahwell.epu.admin.form.ChildStationForm;

public class BeanUtil {

	private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
	
	static {  
//        ConvertUtils.register(new DateConvert(), java.util.Date.class);  
//        ConvertUtils.register(new DateConvert(), java.sql.Date.class);  
//        ConvertUtils.register(new DateConvert(), java.sql.Timestamp.class);  
    }  

	private BeanUtil() {
	}
	
	public static void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void transMap2Bean(Map<String, Object> map, Object obj) {
		if (null == map || null == obj) {
			return;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 *  Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	 * @param map
	 * @param obj
	 */
	public static void transMap2Bean2(Map<String, Object> map, Object obj) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (map.containsKey(key)) {
					Object value = map.get(key);
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * Bean --> Map: 利用Introspector和PropertyDescriptor 将Bean --> Map  
	 * @param obj
	 * @return
	 */
    public static Map<String, Object> transBean2Map(Object obj) {  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
                    map.put(key, value);  
                }  
            }  
        } catch (Exception e) {  
            logger.error(e.getMessage());
        }  
        return map;  
    }  

    public static void main(String[] args) {
		Map<String, Object> ddd = BeanUtil.transBean2Map(new ChildStationForm());
		System.out.println(ddd);
	}
}
