package com.yeahwell.epu.express.enums;

public enum OrderSourceEnum {

	SUNING("00", "苏宁易购"),
	TAOBAO("01", "淘宝"),
	TIANMAO("02", "天猫"),
	JINGDONG("03", "京东"),
    YIHAODIAN("04", "1号店");

	private String code;
	private String cnValue;
	
	private OrderSourceEnum(String code, String cnValue) {
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
		for (OrderSourceEnum item : OrderSourceEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}

}
