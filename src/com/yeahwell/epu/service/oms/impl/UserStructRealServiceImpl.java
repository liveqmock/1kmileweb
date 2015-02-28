package com.yeahwell.epu.service.oms.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.dao.suning.BaseDalClient;
import com.yeahwell.epu.service.oms.UserStructRealService;

@Service
public class UserStructRealServiceImpl implements UserStructRealService{

	@Autowired
	private BaseDalClient baseDalClient;
	
	@Override
	public boolean addUserStructReal(Map<String, Object> paramMap) {
		int affectedRows = 0;
    	affectedRows = baseDalClient.execute("userStructRealMapper.inserUserStructReal", paramMap);
    	if(affectedRows > 0){
    		
    	}
    	return (affectedRows > 0) ? true : false;
	}
	
}
