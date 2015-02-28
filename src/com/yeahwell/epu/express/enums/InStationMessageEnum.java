package com.yeahwell.epu.express.enums;

public enum InStationMessageEnum {

	INSTATION_SUCCESS("00", "入站成功"),
	NOT_THIS_INSTATION("10", "非本站包裹"),
	SYSTEM_ERROR("11", "系统异常"),
	ALREADY_INSTATION("12", "包裹已入站"),
	TRANSACTION_DONE("13", "交易已结束"),
	SMS_NOT_SEND("14", "短信未成功发送");
	
	private String code;
	private String cnValue;
	
	private InStationMessageEnum(String code, String cnValue) {
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
		for (InStationMessageEnum item : InStationMessageEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}

}
