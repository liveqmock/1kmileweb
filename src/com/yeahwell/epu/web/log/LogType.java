package com.yeahwell.epu.web.log;

public enum LogType {
	
	EPU_BIZ("epu.biz"), 
	EPU_SERVICE("epu.service"), 
	EPU_COMMON("epu.common"),
	EPU_SMS("epu.sms"), 
	EPU_EMAIL("epu.email");

	public String val;

	private LogType(String val) {
		this.val = val;
	}
	
}