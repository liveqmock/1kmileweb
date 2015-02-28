package com.yeahwell.epu.express.enums;

public enum PackageLockStatusEnum {

	LOCKED("00", "已锁定"),  
	UNLOCKED("01", "未锁定");

	private String code;
	private String cnValue;
	
	private PackageLockStatusEnum(String code, String cnValue) {
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
		for (PackageLockStatusEnum item : PackageLockStatusEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}

}
