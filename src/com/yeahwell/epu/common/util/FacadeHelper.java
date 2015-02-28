package com.yeahwell.epu.common.util;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.yeahwell.epu.webservice.server.ExpressFacade;
import com.yeahwell.epu.webservice.server.HelloWorldFacade;

public class FacadeHelper {

	private FacadeHelper(){
		
	}
	
	public static HelloWorldFacade getHelloWorldFacade(){
	      //创建WebService客户端代理工厂     
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();     
        //注册WebService接口     
        factory.setServiceClass(HelloWorldFacade.class);     
        //设置WebService地址     
        factory.setAddress("http://127.0.0.1:8080/1kmileweb/webservice/helloWorldFacadeRemote");          
        return (HelloWorldFacade)factory.create();     
	}
	
	public static ExpressFacade getExpressFacade(){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();     
		factory.setServiceClass(ExpressFacade.class);     
		factory.setAddress("http://127.0.0.1:8080/1kmileweb/webservice/expressFacadeRemote");          
		return (ExpressFacade)factory.create();     
	}
	
	
}
