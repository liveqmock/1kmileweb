package com.yeahwell.epu.webservice.server.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.yeahwell.epu.express.business.ExpressBusiness;
import com.yeahwell.epu.express.model.Mypackage;
import com.yeahwell.epu.webservice.server.ExpressFacade;

@WebService(endpointInterface = "com.yeahwell.epu.webservice.server.ExpressFacade", 
serviceName = "expressFacade")
public class ExpressFacadeImpl implements ExpressFacade{
	
	@Autowired
	private ExpressBusiness expressBusiness;

	@WebMethod(operationName = "sendMypackage")
	public boolean sendMypackage(@WebParam(name="stationId")Integer stationId, 
			@WebParam(name="expressCompanyCode") String expressCompanyCode,
			@WebParam(name="expressNo") String expressNo, 
			@WebParam(name="receiveName") String receiveName, 
			@WebParam(name="receiveCellphone") String receiveCellphone,
			@WebParam(name="receiveEmail") String receiveEmail){
		Mypackage mypackage = new Mypackage();
		mypackage.setStationId(stationId);
		mypackage.setExpressCompanyCode(expressCompanyCode);
		mypackage.setExpressNo(expressNo);
		mypackage.setReceiveName(receiveName);
		mypackage.setReceiveCellphone(receiveCellphone);
		mypackage.setReceiveEmail(receiveEmail);
		return expressBusiness.addPackage(mypackage);
	}
}
