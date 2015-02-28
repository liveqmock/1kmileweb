package com.yeahwell.epu.common.webservice;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class WebServiceClient {
	
    public static Object execute(Map<String, Object> paramService, Object... args) throws Exception{
        try {
        	String namespace = (String)paramService.get("namespace");
        	String serviceName = (String)paramService.get("serviceName");
        	String address = (String)paramService.get("address");
        	String ifClass = (String)paramService.get("ifClass");
        	String serviceMethod = (String)paramService.get("serviceMethod");
        	String paramClass = (String)paramService.get("paramClass");
            return execute(namespace, serviceName, address, ifClass, paramClass, serviceMethod, args);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 通过调用CXF生成的客户端代码的方式访问webservice 
     * @param namespace 命名空间  服务表-命名空间（新增）
     * @param serviceName    webservice服务名称  服务表-服务名称
     * @param address    webservice访问地址  服务表-访问路径
     * @param className    webservice接口类全路径  服务表-服务实现类
     * @param paramClass    webservice传入参数全路径   接口表-参数实现类
     * @param serviceMethod    webservcie服务方法名   接口表-操作方法
     * @param arg 传递参数
     * @return
     * @throws Exception
     */
    public static Object execute(String namespace, String serviceName, String address, String className, String paramClass, String serviceMethod,Object... args) throws Exception{
        try {
            Service service = Service.create(new URL(address),new QName(namespace, serviceName));    
            Object obj = service.getPort(Class.forName(className));
            Method method = obj.getClass().getMethod(serviceMethod,Class.forName(paramClass));
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw e;
        }
    }
}
