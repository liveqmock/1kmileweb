package com.yeahwell.epu.oms.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.yeahwell.epu.oms.enums.MasterStationStatusEnum;

public class MasterStation {

	private Integer stationId;
	
	private String stationName;
	
	private String stationShortName;
	
	private Date contractTime;
	
	private String cellphone;
	
	private String fixphone;
	
	private String status;
	
	private String statusDesc;

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
		if(StringUtils.isNotEmpty(status)){
			setStatusDesc(MasterStationStatusEnum.byCode(status));
		}
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	
}
