package com.yeahwell.epu.webservice.server;

import javax.jws.WebService;

@WebService
public interface HelloWorldFacade {

	public String sayHello(String username);
}
