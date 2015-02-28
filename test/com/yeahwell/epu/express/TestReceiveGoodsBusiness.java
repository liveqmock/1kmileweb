package com.yeahwell.epu.express;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yeahwell.epu.base.TestBaseTemplate;
import com.yeahwell.epu.express.business.ExpressBusiness;
import com.yeahwell.epu.express.enums.ExpressCompanyEnum;
import com.yeahwell.epu.express.model.Mypackage;

public class TestReceiveGoodsBusiness extends TestBaseTemplate {

	private static Logger logger = LoggerFactory
			.getLogger(TestReceiveGoodsBusiness.class);
	
	@Autowired
	private ExpressBusiness expressBusiness;
	
	private List<Mypackage> initPackageList() {
		
		List<Mypackage> packageList = new ArrayList<Mypackage>();
		
		Mypackage mypackage = null;
		for (int i = 0; i < 50; i++) {
			mypackage = new Mypackage(); 
			mypackage.setStationId(7);
			mypackage.setExpressCompanyCode(ExpressCompanyEnum.SHENTONG.getCode());
			mypackage.setExpressNo("expressNo" + i);
			mypackage.setReceiveName("刘东强" + i);
			mypackage.setReceiveCellphone("18616968346");
			mypackage.setReceiveEmail("abctest" + i + "@163.com");
			packageList.add(mypackage);
		}
		return packageList;
	}

	@Test
	public void testAddPakcage() {
		List<Mypackage> packageList = initPackageList();
		for(Mypackage mypackage : packageList){
			expressBusiness.addPackage(mypackage);
		}
	}

}
