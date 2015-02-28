package com.yeahwell.epu.express.model;

import java.math.BigDecimal;
import java.util.Date;

public class MypackageExtra {
	
	private Integer packageId;
	private String orderSourceCode;
	private String orderNo;
	private String merchantCode;
	private String merchantName;
	
	private String receiveProvinceCode;
	private String receiveCityCode;
	private String receiveDistrictCode;
	private String receiveStreetCode;
	private String receiveDetailAddress;
	private String receiveWholeAddress;
	
	private String sendName;
	private String sendCellphone;
	private String sendFixphone;
	private String sendProvinceCode;
	private String sendCityCode;
	private String sendDistrictCode;
	private String sendStreetCode;
	private String sendDetailAddress;
	private String sendWholeAddress;
	
	private String returnName;
	private String returnCellphone;
	private String returnFixphone;
	private String returnProvinceCode;
	private String returnCityCode;
	private String returnDistrictCode;
	private String returnStreetCode;
	private String returnDetailAddress;
	private String returnWholeAddress;
	
	private String payStatus;
	private String payWayCode;
	private BigDecimal payRmb;
	private BigDecimal debtRmb;
	
	private String lockStatus;
	private Date lockTime;
	private String lockDesc;
	
}
