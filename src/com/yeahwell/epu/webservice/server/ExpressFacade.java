package com.yeahwell.epu.webservice.server;

import javax.jws.WebService;

@WebService
public interface ExpressFacade {

	public boolean sendMypackage(Integer stationId, String expressCompanyCode,
			String expressNo, String receiveName, String receiveCellphone,
			String receiveEmail);
}
