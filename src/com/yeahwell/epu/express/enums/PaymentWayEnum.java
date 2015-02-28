package com.yeahwell.epu.express.enums;

public enum PaymentWayEnum {

	ONLINE_PAY("00", "在线支付"),
	CASH_ON_DELIVERY ("01", "货到付款");

	private String code;
	private String cnValue;
	
	private PaymentWayEnum(String code, String cnValue) {
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
		for (PaymentWayEnum item : PaymentWayEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}

}
