package com.yeahwell.epu.oms.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CorpStructTypeEnum {

	CHILD_STATION("00", "子站点"),
	MASTER_STATION("01", "合作商"),
	REGION_STATION("02", "分公司");

	private String code;
	private String cnValue;
	
	private CorpStructTypeEnum(String code, String cnValue) {
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
		for (CorpStructTypeEnum item : CorpStructTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}
	
	public static List<Map<String, Object>> toList(){
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> tempMap = null;
		for (CorpStructTypeEnum item : CorpStructTypeEnum.values()) {
			tempMap = new HashMap<String, Object>();
			tempMap.put("code", item.getCode());
			tempMap.put("cnValue", item.getCnValue());
			resultList.add(tempMap);
		}
		return resultList;
	}

}
