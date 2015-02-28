package com.yeahwell.epu.service.oms.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.dao.suning.BaseDalClient;
import com.yeahwell.epu.service.oms.ChildStationStructRealService;

@Service
public class ChildStationStructRealServiceImpl implements ChildStationStructRealService{

	@Autowired
	private BaseDalClient baseDalClient;
	
	@Override
	public boolean addChildStationStructReal(Map<String, Object> paramMap) {
		int affectedRows = 0;
		affectedRows = baseDalClient.execute(
				"childStationStructRealMapper.insertChildStationStructReal", paramMap);
		return (affectedRows > 0) ? true : false;
	}

}
