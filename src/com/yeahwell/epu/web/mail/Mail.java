package com.yeahwell.epu.web.mail;

import java.util.List;

/**
 * 发送邮件需要的所有信息
 * 
 */
public class Mail {

	// 发送邮件的服务器的IP和端口
	private String hostName;
	
	private int portNumber = 25;
	
	// 邮件发送者的地址
	private MailUser fromUser;
	
	// 邮件接收者的地址
	private List<MailUser> toUsers;
	
	// 抄送人地址
	private List<MailUser> ccUsers;
	
	// 登陆邮件发送服务器的用户名和密码
	private String userName;
	
	private String password;
	
	// 是否需要身份验证 默认设置为false
	private boolean tls = false;
	
	// 还有一个设置SMTP 安全：SSL
	
	// 字符设置 默认设置为utf-8
	private String charset = "UTF-8";
	
	// 邮件主题
	private String subject;
	
	// 邮件的文本内容
	private String content;
	
	private List<MailAttach> attaches;

	public Mail() {

	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public MailUser getFromUser() {
		return fromUser;
	}

	public void setFromUser(MailUser fromUser) {
		this.fromUser = fromUser;
	}

	public List<MailUser> getToUsers() {
		return toUsers;
	}

	public void setToUsers(List<MailUser> toUsers) {
		this.toUsers = toUsers;
	}

	public List<MailUser> getCcUsers() {
		return ccUsers;
	}

	public void setCcUsers(List<MailUser> ccUsers) {
		this.ccUsers = ccUsers;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isTls() {
		return tls;
	}

	public void setTls(boolean tls) {
		this.tls = tls;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<MailAttach> getAttaches() {
		return attaches;
	}

	public void setAttaches(List<MailAttach> attaches) {
		this.attaches = attaches;
	}
	
	

}
