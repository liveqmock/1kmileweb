package com.yeahwell.epu.express.enums;

public enum PaymentStatusEnum {

	UNPAID("00", "未支付"),
	PAID("01", "已支付");

	private String code;
	private String cnValue;
	
	private PaymentStatusEnum(String code, String cnValue) {
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
		for (PaymentStatusEnum item : PaymentStatusEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}

}
