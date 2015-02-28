package com.yeahwell.epu.oms.enums;


public enum UserTypeEnum {
	
	SUPER_ADMIN("A", "后台管理员"),
	MASTER_ADMIN("M", "合作商管理员"),
	REGION_ADMIN("R", "分公司管理员"),
	CHILD_STATION("S", "子站点管理员"),
	CUSTOMER("C", "普通用户");
	
	private String code;
	private String cnValue;
	
	private UserTypeEnum(String code, String cnValue) {
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
		for (UserTypeEnum item : UserTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}
}
