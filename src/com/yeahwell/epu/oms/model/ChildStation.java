package com.yeahwell.epu.oms.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.yeahwell.epu.oms.enums.ChildStationStatusEnum;
import com.yeahwell.epu.oms.enums.ChildStationTypeEnum;

public class ChildStation {

	private Integer stationId;
	
	private Integer structId;
	
	private String stationName;
	
	private String type;
	
	private String typeDesc;
	
	private String businessTime;
	
	private String stationDesc;
	
	private String provinceCode;
	
	private String cityCode;
	
	private String districtCode;
	
	private String streetCode;
	
	private String detailAddress;
	
	/**
	 * 完整地址
	 */
	private String wholeAddress;
	
	private String linkman;
	
	private String cellphone;
	
	private String fixphone;
	
	private String status;
	
	private String statusDesc;
	
	private Date lockTime;
	
	private String lockDesc;
	
	private Integer lockCount;

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public Integer getStructId() {
		return structId;
	}

	public void setStructId(Integer structId) {
		this.structId = structId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if(StringUtils.isNotEmpty(type)){
			setTypeDesc(ChildStationTypeEnum.byCode(type));
		}
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	public String getStationDesc() {
		return stationDesc;
	}

	public void setStationDesc(String stationDesc) {
		this.stationDesc = stationDesc;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getStreetCode() {
		return streetCode;
	}

	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getWholeAddress() {
		return wholeAddress;
	}

	public void setWholeAddress(String wholeAddress) {
		this.wholeAddress = wholeAddress;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
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
			setStatusDesc(ChildStationStatusEnum.byCode(status));
		}
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public String getLockDesc() {
		return lockDesc;
	}

	public void setLockDesc(String lockDesc) {
		this.lockDesc = lockDesc;
	}

	public Integer getLockCount() {
		return lockCount;
	}

	public void setLockCount(Integer lockCount) {
		this.lockCount = lockCount;
	}
	
}
