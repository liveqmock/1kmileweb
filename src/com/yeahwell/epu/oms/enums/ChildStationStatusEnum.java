package com.yeahwell.epu.oms.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ChildStationStatusEnum {

	LOCKED("00", "不启用"),
	UNLOCKED("01", "启用"),
	APPLY_UNLOCKED ("02", "申请启用中");

	private String code;
	private String cnValue;
	
	private ChildStationStatusEnum(String code, String cnValue) {
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
		for (ChildStationStatusEnum item : ChildStationStatusEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}

	public static List<Map<String, Object>> toList(){
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> tempMap = null;
		for (ChildStationStatusEnum item : ChildStationStatusEnum.values()) {
			tempMap = new HashMap<String, Object>();
			tempMap.put("code", item.getCode());
			tempMap.put("cnValue", item.getCnValue());
			resultList.add(tempMap);
		}
		return resultList;
	}
}
