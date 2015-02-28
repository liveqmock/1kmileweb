package com.yeahwell.epu.express.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ExpressCompanyEnum {

	SHENTONG("00", "申通快递"),
	YUANTONG("01", "圆通快递"),
	SHUNFENG("02", "顺丰速递"),
	JINGDONG("03", "京东物流"),
    SUNING("04", "苏宁物流"),
    ZHAIJISONG("05", "宅急送"),
    YUNDA("06", "韵达快递"),
    ZHONGTONG("07", "中通快递"),
    TIANTIAN("07", "天天快递"),
    HUITONG("07", "汇通快递"),
	EMS("10", "邮政EMS");

	private String code;
	private String cnValue;
	
	private ExpressCompanyEnum(String code, String cnValue) {
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
		for (ExpressCompanyEnum item : ExpressCompanyEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}
	
	public static List<Map<String, Object>> toList(){
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> tempMap = null;
		for (ExpressCompanyEnum item : ExpressCompanyEnum.values()) {
			tempMap = new HashMap<String, Object>();
			tempMap.put("code", item.getCode());
			tempMap.put("cnValue", item.getCnValue());
			resultList.add(tempMap);
		}
		return resultList;
	}

}
