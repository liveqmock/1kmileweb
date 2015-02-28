package com.yeahwell.epu.express.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.yeahwell.epu.express.enums.ExpressCompanyEnum;
import com.yeahwell.epu.express.enums.PackageStatusEnum;

public class Mypackage {
	
	private Integer packageId;
	private Integer stationId;
	private String expressNo;
	private String expressCompanyCode;
	private String expressCompanyCodeDesc;
	private String receiveName;
	private String receiveCellphone;
	private String receiveFixphone;
	private String receiveEmail;
	private String packageStatus;
	private String packageStatusDesc;
	private String pickupCode;
	private Date inTime;
	private Date outTime;
	private Date outEndTime;
	private Integer emailSendTime;
	private Integer mobileSendTime;
	
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getExpressCompanyCode() {
		return expressCompanyCode;
	}
	public void setExpressCompanyCode(String expressCompanyCode) {
		this.expressCompanyCode = expressCompanyCode;
		if(StringUtils.isNotEmpty(expressCompanyCode)){
			this.expressCompanyCodeDesc = ExpressCompanyEnum.byCode(expressCompanyCode);
		}
	}
	public String getExpressCompanyCodeDesc() {
		return expressCompanyCodeDesc;
	}
	public void setExpressCompanyCodeDesc(String expressCompanyCodeDesc) {
		this.expressCompanyCodeDesc = expressCompanyCodeDesc;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getReceiveCellphone() {
		return receiveCellphone;
	}
	public void setReceiveCellphone(String receiveCellphone) {
		this.receiveCellphone = receiveCellphone;
	}
	public String getReceiveFixphone() {
		return receiveFixphone;
	}
	public void setReceiveFixphone(String receiveFixphone) {
		this.receiveFixphone = receiveFixphone;
	}
	public String getReceiveEmail() {
		return receiveEmail;
	}
	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}
	public String getPackageStatus() {
		return packageStatus;
	}
	public void setPackageStatus(String packageStatus) {
		this.packageStatus = packageStatus;
		if(StringUtils.isNotEmpty(packageStatus)){
			setPackageStatusDesc(PackageStatusEnum.byCode(packageStatus));
		}
	}
	public String getPackageStatusDesc() {
		return packageStatusDesc;
	}
	public void setPackageStatusDesc(String packageStatusDesc) {
		this.packageStatusDesc = packageStatusDesc;
	}
	public String getPickupCode() {
		return pickupCode;
	}
	public void setPickupCode(String pickupCode) {
		this.pickupCode = pickupCode;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public Date getOutEndTime() {
		return outEndTime;
	}
	public void setOutEndTime(Date outEndTime) {
		this.outEndTime = outEndTime;
	}
	public Integer getEmailSendTime() {
		return emailSendTime;
	}
	public void setEmailSendTime(Integer emailSendTime) {
		this.emailSendTime = emailSendTime;
	}
	public Integer getMobileSendTime() {
		return mobileSendTime;
	}
	public void setMobileSendTime(Integer mobileSendTime) {
		this.mobileSendTime = mobileSendTime;
	}
	
}
