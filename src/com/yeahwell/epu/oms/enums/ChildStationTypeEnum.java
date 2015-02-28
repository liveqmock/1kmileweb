package com.yeahwell.epu.oms.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ChildStationTypeEnum {

	TYPE_0000("0000", "便利店"),
	TYPE_0001("0001", "水果超市"),
	TYPE_0002("0002", "理发店"),
	TYPE_0003("0101", "招待所"),
	
	TYPE_0100("0100", "连锁超市"),
	TYPE_0101("0101", "连锁酒店"),
	TYPE_0102("0102", "物业"),
	
	TYPE_9999("9999", "其他");

	private String code;
	private String cnValue;
	
	private ChildStationTypeEnum(String code, String cnValue) {
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
		for (ChildStationTypeEnum item : ChildStationTypeEnum.values()) {
			if (item.getCode().equals(code)) {
				return item.getCnValue();
			}
		}
		return null;
	}
	
	public static List<Map<String, Object>> toList(){
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> tempMap = null;
		for (ChildStationTypeEnum item : ChildStationTypeEnum.values()) {
			tempMap = new HashMap<String, Object>();
			tempMap.put("code", item.getCode());
			tempMap.put("cnValue", item.getCnValue());
			resultList.add(tempMap);
		}
		return resultList;
	}

}
