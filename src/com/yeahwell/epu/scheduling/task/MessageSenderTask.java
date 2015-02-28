package com.yeahwell.epu.scheduling.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yeahwell.epu.express.business.ExpressBusiness;
import com.yeahwell.epu.express.business.ExpressNoticeBusiness;
import com.yeahwell.epu.express.model.Mypackage;

public class MessageSenderTask {
	
	private static final Logger logger = LoggerFactory
			.getLogger(MessageSenderTask.class);
	
	@Autowired
	private ExpressBusiness expressBusiness;
	
	@Autowired
	private ExpressNoticeBusiness expressNoticeBusiness;

	public void sendFirstEmail() {
		logger.info("启动定时任务------发送入站信息到邮箱");
		List<Mypackage> mypackageList = expressBusiness.findPackageNoticeEmail();
		for(Mypackage mypackage : mypackageList){
			expressNoticeBusiness.sendNoticeMail(mypackage);
		}
	}
	
	public void sendFirstMobile() {
		logger.info("启动定时任务------发送入站信息到手机号");
		List<Mypackage> mypackageList = expressBusiness.findPackageNoticeMobile();
		for(Mypackage mypackage : mypackageList){
			expressNoticeBusiness.sendNoticeMobile(mypackage);
		}
	}
	
	public void sendAgainEmail() {
		logger.info("启动定时任务------发送二次提醒信息到邮箱");
		List<Mypackage> mypackageList = expressBusiness.findPackageNoticeAgain();
		for(Mypackage mypackage : mypackageList){
			expressNoticeBusiness.sendNoticeMail(mypackage);
		}
	}
	
	public void sendAgainMobile() {
		logger.info("启动定时任务------发送二次提醒信息到手机号");
		List<Mypackage> mypackageList = expressBusiness.findPackageNoticeAgain();
		for(Mypackage mypackage : mypackageList){
			expressNoticeBusiness.sendNoticeMobile(mypackage);
		}
	}
	
	public void autoCloseExpress() {
		logger.info("启动定时任务------自动关闭超期未提货的交易");
		expressBusiness.autoClose();
	}
}