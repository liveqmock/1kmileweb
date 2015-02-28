package com.yeahwell.epu.web.sms;

import java.util.Map;
import java.util.UUID;

public class SingleSms {
	
	private String id = UUID.randomUUID().toString();
	
	private String templateId;
	
	private String businessId;
	
	private String phoneNo;
	
	private Map<String, String> params;
	
	private String verifyCode;
	
	private String sendTime;
	
	private String status;

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public Map<String, String> getParams() {
		return this.params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessId() {
		return this.businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getVerifyCode() {
		return this.verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return "Sms [id=" + this.id + ", templateId=" + this.templateId
				+ ", businessId=" + this.businessId + ", phoneNo="
				+ this.phoneNo + ", params=" + this.params + "]";
	}
}