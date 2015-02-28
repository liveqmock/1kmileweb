package com.yeahwell.epu.cms.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum ArticleCategoryEnum {
	
	STARTED("00", "开始使用"),
	FAQ("01", "常见问题"),
	ADS("02", "商品推广");
	
	private String code;
	private String cnValue;
	
	private ArticleCategoryEnum(String code, String cnValue) {
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
		for (ArticleCategoryEnum item : ArticleCategoryEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}
	
	public static List<Map<String, Object>> toList(){
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> tempMap = null;
		for (ArticleCategoryEnum item : ArticleCategoryEnum.values()) {
			tempMap = new HashMap<String, Object>();
			tempMap.put("code", item.getCode());
			tempMap.put("cnValue", item.getCnValue());
			resultList.add(tempMap);
		}
		return resultList;
	}
}
