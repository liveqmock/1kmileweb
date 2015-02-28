package com.yeahwell.epu.cms.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum ArticleStatusEnum {
	
	WAIT_FOR_AUDIT("00", "等待审核"),
	PUBLISHED("01", "已发布"),
	RECYCLE("02", "已删除");
	
	private String code;
	private String cnValue;
	
	private ArticleStatusEnum(String code, String cnValue) {
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
		for (ArticleStatusEnum item : ArticleStatusEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}
	
	public static List<Map<String, Object>> toList(){
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> tempMap = null;
		for (ArticleStatusEnum item : ArticleStatusEnum.values()) {
			tempMap = new HashMap<String, Object>();
			tempMap.put("code", item.getCode());
			tempMap.put("cnValue", item.getCnValue());
			resultList.add(tempMap);
		}
		return resultList;
	}
}
