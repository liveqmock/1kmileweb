package com.yeahwell.epu.web.mail;

public class MailUser {

	/**
	 * 电子邮箱号
	 */
	private String email;

	/**
	 * 用户名
	 */
	private String name;
	
	public MailUser(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
