package com.yeahwell.epu.webservice.server.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.yeahwell.epu.webservice.server.HelloWorldFacade;

@WebService(endpointInterface = "com.yeahwell.epu.webservice.server.HelloWorldFacade", 
serviceName = "helloWorldFacade")
public class HelloWorldFacadeImpl implements HelloWorldFacade {

	@WebMethod(operationName = "sayHello")
	public String sayHello(@WebParam(name="username") String username) {
		return "你好," + username;
	}

}
