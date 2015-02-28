package com.yeahwell.epu.oms.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.epu.common.util.BeanUtil;
import com.yeahwell.epu.oms.enums.CorpStructTypeEnum;
import com.yeahwell.epu.oms.model.CorpStruct;
import com.yeahwell.epu.service.oms.CorpStructService;

@Service
public class CorpStructBusiness {

	@Autowired
	private CorpStructService corpStructService;
	
	public CorpStruct findCorpStructById(Integer structId){
		CorpStruct corpStruct = new CorpStruct();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("structId", structId);
		Map<String, Object> corpStructMap = corpStructService.findCorpStructById(paramMap);
		if(null != corpStructMap){
			BeanUtil.transMap2Bean(corpStructMap, corpStruct);
		}
		return corpStruct;
	}
	
	public List<CorpStruct> findCorpStructByParentId(Integer parentId, Integer level){
		List<CorpStruct> corpStructList = new ArrayList<CorpStruct>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", parentId);
		if(null != level){
			paramMap.put("level", level);
		}
		CorpStruct corpStruct = null;
		List<Map<String, Object>> corpStructMapList = corpStructService.findCorpStructByParentId(paramMap);
		for(Map<String, Object> corpStructMap : corpStructMapList){
			corpStruct = new CorpStruct();
			BeanUtil.transMap2Bean(corpStructMap, corpStruct);
			corpStructList.add(corpStruct);
		}
		return corpStructList;
	}
	
	public List<CorpStruct> findCorpStructAll(){
		return findCorpStructAll(null);
	}
	
	public List<CorpStruct> findCorpStructMasterStation(){
		return findCorpStructAll(Arrays.asList(CorpStructTypeEnum.MASTER_STATION.getCode()));
	}
	
	public List<CorpStruct> findCorpStructOfChildStation(){
		return findCorpStructAll(Arrays.asList(CorpStructTypeEnum.CHILD_STATION.getCode()));
	}
	
	public List<CorpStruct> findCorpStructOfCompany(){
		return findCorpStructAll(Arrays.asList(CorpStructTypeEnum.MASTER_STATION.getCode(), 
				CorpStructTypeEnum.REGION_STATION.getCode()));
	}
	
	public List<CorpStruct> findCorpStructAll(List<String> typeList){
		List<CorpStruct> corpStructList = new ArrayList<CorpStruct>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(null != typeList && typeList.size() > 0){
			paramMap.put("typeList", typeList);
		}
		CorpStruct corpStruct = null;
		List<Map<String, Object>> corpStructMapList = corpStructService.findCorpStructAll(paramMap);
		for(Map<String, Object> corpStructMap : corpStructMapList){
			corpStruct = new CorpStruct();
			BeanUtil.transMap2Bean(corpStructMap, corpStruct);
			corpStructList.add(corpStruct);
		}
		return corpStructList;
	}
	
}
