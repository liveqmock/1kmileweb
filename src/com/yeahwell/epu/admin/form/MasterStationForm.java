package com.yeahwell.epu.admin.form;

import java.util.Date;

public class MasterStationForm {

	private Integer stationId;
	
	private String stationName;
	
	private String stationShortName;
	
	private Date contractTime;
	
	private String cellphone;
	
	private String fixphone;
	
	private String status;

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationShortName() {
		return stationShortName;
	}

	public void setStationShortName(String stationShortName) {
		this.stationShortName = stationShortName;
	}

	public Date getContractTime() {
		return contractTime;
	}

	public void setContractTime(Date contractTime) {
		this.contractTime = contractTime;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getFixphone() {
		return fixphone;
	}

	public void setFixphone(String fixphone) {
		this.fixphone = fixphone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
