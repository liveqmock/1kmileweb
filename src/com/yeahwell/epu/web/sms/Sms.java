package com.yeahwell.epu.web.sms;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class Sms {
	
	private String id = UUID.randomUUID().toString();
	
	private String templateId;
	
	private String businessId;
	
	private String[] phone;
	
	private Map<String, String> params;

	public String[] getPhone() {
		return this.phone;
	}

	public void setPhone(String[] phone) {
		this.phone = phone;
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

	public String toString() {
		return "Sms [id=" + this.id + ", templateId=" + this.templateId
				+ ", businessId=" + this.businessId + ", phone="
				+ Arrays.toString(this.phone) + ", params=" + this.params + "]";
	}
}