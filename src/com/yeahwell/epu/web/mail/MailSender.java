package com.yeahwell.epu.web.mail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class MailSender {

	/**
	 * 发送含有Html格式的内容
	 * 
	 * @param mail
	 * @return
	 */
	public static boolean sendEmail(final Mail mail) {
		//1. 设置基本参数
		HtmlEmail email = new HtmlEmail();
		email.setHostName(mail.getHostName());
		email.setSmtpPort(mail.getPortNumber());
		email.setTLS(mail.isTls());
		email.setCharset(mail.getCharset());
		email.setAuthentication(mail.getUserName(), mail.getPassword());
		boolean result = false;
		try {
			//2. 设置标题和内容
			email.setSubject(mail.getSubject());
			email.setHtmlMsg(mail.getContent());
			//3. 设置发件人
			email.setFrom(mail.getFromUser().getEmail(), mail.getFromUser().getName());
			//4. 设置抄送人
			if (null != mail.getCcUsers() && mail.getCcUsers().size() > 0){
				for(MailUser ccUser : mail.getCcUsers()){
					email.addCc(ccUser.getEmail(), ccUser.getName());
				}
			}
			//5. 设置附件
			if (null != mail.getAttaches()
					&& mail.getAttaches().size() > 0) {
				for (MailAttach attach : mail.getAttaches()) {
					EmailAttachment emailAttachment = new EmailAttachment();
					emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
					emailAttachment.setDescription(attach.getDescription());
					emailAttachment.setName(attach.getName());
					// 发送本地附件
					emailAttachment.setPath(attach.getPath());
					// 发送网上附件
					emailAttachment.setURL(attach.getUrl());
					email.attach(emailAttachment);
				}
			}
			//6. 设置收件人
			if (null != mail.getToUsers()
					&& mail.getToUsers().size() > 0) {
				for (MailUser toUser : mail.getToUsers()) {
					email.addTo(toUser.getEmail(), toUser.getName());
					email.send();
				}
			} else {
				throw new RuntimeException("收件人不能为空");
			}
			//发送邮件
			result = true;
		} catch (EmailException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Mail mail = new Mail();
		mail.setPortNumber(25);
		mail.setTls(true);
		
		mail.setHostName("smtp.163.com");
		mail.setUserName("yeahwell19920525@163.com");
		mail.setPassword("yjw19920525");
		mail.setFromUser(new MailUser("yeahwell19920525@163.com", "1公里智能技术有限公司"));

//		mail.setHostName("smtp.qq.com");
//		mail.setUserName("yeahwell19920525@qq.com");
//		mail.setPassword("shalou962464");
//		mail.setFromUser(new MailUser("yeahwell19920525@qq.com", "1公里智能技术有限公司"));
		
//		mail.setHostName("smtp.gmail.com");
//		mail.setUserName("wesley.yan92@gmail.com");
//		mail.setPassword("yjw19920525");
//		mail.setFromUser(new MailUser("wesley.yan92@gmail.com", "1公里智能技术有限公司"));
		
		List<MailUser> toUsers = new ArrayList<MailUser>();
		toUsers.add(new MailUser("yeahwell19920525@qq.com", "颜佳伟"));
		//toUsers.add(new MailUser("2847740364@qq.com", "湘海"));
		mail.setToUsers(toUsers);
		mail.setSubject("快递到达通知" + "运单号");
		mail.setContent("您的快件" + "运单号" + "已到达" + "易提站地址");
		MailSender.sendEmail(mail);
		System.out.println("邮件发送成功");
	}

}
