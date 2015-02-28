package com.yeahwell.epu.service.oms.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.dao.suning.BaseDalClient;
import com.yeahwell.epu.service.oms.CorpStructService;

@Service
public class CorpStructServiceImpl implements CorpStructService {

	@Autowired
	private BaseDalClient baseDalClient;
	
	@Override
	public boolean addCorpStruct(Map<String, Object> paramMap) {
		int affectedRows = 0;
		affectedRows = baseDalClient.execute(
				"corpStructMapper.insertCorpStruct", paramMap);
		return (affectedRows > 0) ? true : false;
	}
	
	@Override
	public Map<String, Object> findCorpStructById(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForMap(
				"corpStructMapper.queryCorpStructById", paramMap);
	}

	@Override
	public List<Map<String, Object>> findCorpStructByParentId(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForList(
				"corpStructMapper.queryCorpStructByParentId", paramMap);
	}

	@Override
	public List<Map<String, Object>> findCorpStructAll(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForList(
				"corpStructMapper.queryCorpStructAll", paramMap);
	}

}
