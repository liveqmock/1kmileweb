package com.yeahwell.epu.express.enums;

public enum PackageStatusEnum {

	WAIT_IN_STATION("00", "待入站"),
	WAIT_PICKUP("01", "待提货"),
	TRANSACTION_SUCCESS("02", "交易成功"),
	TRANSACTION_CLOSE("03", "交易关闭");

	private String code;
	private String cnValue;

	private PackageStatusEnum(String code, String cnValue) {
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
		for (PackageStatusEnum item : PackageStatusEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}

}
