package com.yeahwell.epu.service.oms.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.dao.suning.BaseDalClient;
import com.yeahwell.epu.service.oms.MasterStationStructRealService;

@Service
public class MasterStationStructRealServiceImpl implements MasterStationStructRealService{

	@Autowired
	private BaseDalClient baseDalClient;
	
	@Override
	public boolean addMasterStationStructReal(Map<String, Object> paramMap) {
		int affectedRows = 0;
		affectedRows = baseDalClient.execute(
				"masterStationStructRealMapper.insertMasterStationStructReal", paramMap);
		return (affectedRows > 0) ? true : false;
	}

}
