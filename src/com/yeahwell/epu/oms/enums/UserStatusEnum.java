package com.yeahwell.epu.oms.enums;


public enum UserStatusEnum {
	
	INACTIVE("I", "未激活"),
	NORMAL("T", "正常"),
	UNAVAILABLE("U", "不可用");
	
	private String code;
	private String cnValue;
	
	private UserStatusEnum(String code, String cnValue) {
		this.code = code;
		this.cnValue = cnValue;
	}

	public String getCode() {
		return code;
	}

	public String getCnValue() {
		return cnValue;
	}

	public static String byCode(final String code) {
		for (UserStatusEnum item : UserStatusEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}
}
