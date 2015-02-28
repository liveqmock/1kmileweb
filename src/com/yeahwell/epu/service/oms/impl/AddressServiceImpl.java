package com.yeahwell.epu.service.oms.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.dao.suning.BaseDalClient;
import com.yeahwell.epu.service.oms.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private BaseDalClient baseDalClient;
	
	@Autowired
	public List<Map<String, Object>> listAddressAll(Map<String,Object> paramMap){
		return baseDalClient.queryForList("addressMapper.queryAddressAll", paramMap);
	}

	@Override
	public List<Map<String, Object>> listProvinces(Map<String, Object> paramMap) {
		return baseDalClient.queryForList("addressMapper.queryProvinces", paramMap);
	}

	@Override
	public List<Map<String, Object>> listCitys(Map<String, Object> paramMap) {
		return baseDalClient.queryForList("addressMapper.queryCitys", paramMap);
	}

	@Override
	public List<Map<String, Object>> listDistricts(Map<String, Object> paramMap) {
		return baseDalClient.queryForList("addressMapper.queryDistricts", paramMap);
	}

	@Override
	public List<Map<String, Object>> listStreets(Map<String, Object> paramMap) {
		return null;
	}

	@Override
	public Map<String, Object> findProvince(Map<String, Object> paramMap) {
		return baseDalClient.queryForMap("addressMapper.queryProvinces", paramMap);
	}

	@Override
	public Map<String, Object> findCity(Map<String, Object> paramMap) {
		return baseDalClient.queryForMap("addressMapper.queryCitys", paramMap);
	}

	@Override
	public Map<String, Object> findDistrict(Map<String, Object> paramMap) {
		return baseDalClient.queryForMap("addressMapper.queryDistricts", paramMap);
	}

	
}
