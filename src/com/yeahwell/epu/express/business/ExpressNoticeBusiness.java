package com.yeahwell.epu.express.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.epu.common.util.DateUtil;
import com.yeahwell.epu.express.model.Mypackage;
import com.yeahwell.epu.oms.business.ChildStationBusiness;
import com.yeahwell.epu.oms.model.ChildStation;
import com.yeahwell.epu.web.mail.Mail;
import com.yeahwell.epu.web.mail.MailSender;
import com.yeahwell.epu.web.mail.MailUser;
import com.yeahwell.epu.web.sms.SmsSender;

@Service
public class ExpressNoticeBusiness {
	
	private Logger logger = LoggerFactory.getLogger(ExpressNoticeBusiness.class);
	
	@Autowired
	private ChildStationBusiness childStationBusiness;
	
	@Autowired
	private ExpressBusiness expressBusiness;
	
	public void sendNoticeMail(Mypackage mypackage){
		sendNoticeMobileSingle(mypackage, mypackage.getPickupCode());
	}
	
	public void sendNoticeMailSingle(Mypackage mypackage, String pickupCode){
		logger.info("发送提醒邮件");
		final ChildStation childStation = childStationBusiness.findChildStationById(mypackage.getStationId());
		Mail mail = new Mail();
		// 1. 设置基本参数
		mail.setTls(true);

		// 2. 设置发件人
		mail.setHostName("smtp.163.com");
		mail.setPortNumber(25);
		mail.setUserName("yeahwell19920525@163.com");
		mail.setPassword("yjw19920525");
		mail.setFromUser(new MailUser("yeahwell19920525@163.com", "1公里智能技术有限公司"));

		// 3. 设置主题和内容
		mail.setSubject(String.format("您的快递%s已到达%s", mypackage.getExpressNo(),
				childStation.getStationName()));
		mail.setContent(String.format(
				"快递%s已到达%s,请于%s天内到%s(联系方式: %s, %s)取件,提货码%s【1公里】",
				mypackage.getExpressNo(), childStation.getStationName(),
				DateUtil.dateDiff(mypackage.getOutEndTime(), new Date()), childStation.getWholeAddress(), childStation.getFixphone(),
				childStation.getCellphone(), pickupCode));
		
		// 4. 设置收件人
		List<MailUser> toUsers = new ArrayList<MailUser>();
		toUsers.add(new MailUser(mypackage.getReceiveEmail(), mypackage.getReceiveName()));
		mail.setToUsers(toUsers);
		
		MailSender.sendEmail(mail);
		// 5. 执行发送邮件
		//TODO 邮件发送失败应该不执行以下代码
		expressBusiness.sendEmailIncr(mypackage.getPackageId());
	}
	
	public void sendNoticeMobile(Mypackage mypackage){
		sendNoticeMobileSingle(mypackage, mypackage.getPickupCode());
	}
	
	
	public void sendNoticeMobileSingle(Mypackage mypackage, String pickupCode){
		logger.info("发送提醒短信");
		final ChildStation childStation = childStationBusiness.findChildStationById(mypackage.getStationId());
		String sms = String.format("快递%s已到达%s,请于%s天内取件,提货码%s【1公里】",mypackage.getExpressNo(), childStation.getStationName(),
				DateUtil.dateDiff(mypackage.getOutEndTime(), new Date()), pickupCode);
		try{
			//真实发送短信，收费
//			SmsSender.sendReal(sms, mypackage.getReceiveCellphone());
			//模拟发送短信
			SmsSender.send(sms);
			expressBusiness.sendMobileIncr(mypackage.getPackageId());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(String.format(
				"您的快递%s已到达%s,请于%s天内到%s(联系方式: %s, %s)取件,提货码%s",
				1,2,
				3, 4, 5,
				6, 7));
	}
	
}
