package com.yeahwell.epu.service.oms.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.dao.suning.BaseDalClient;
import com.yeahwell.epu.oms.enums.CorpStructTypeEnum;
import com.yeahwell.epu.service.oms.ChartService;

@Service
public class ChartServiceImpl implements ChartService{
	
	@Autowired
	private BaseDalClient baseDalClient;

	@Override
	public List<Map<String, Object>> findChildStationProvinceChart(
			Map<String, Object> paramMap) {
		paramMap.put("corpStructType", CorpStructTypeEnum.CHILD_STATION.getCode());
		return baseDalClient.queryForList(
				"chartMapper.queryChildStationProvinceChart", paramMap);
	}

	@Override
	public List<Map<String, Object>> findPackageInStationTimeChart(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForList(
				"chartMapper.queryInStationTime", paramMap);
	}

	@Override
	public List<Map<String, Object>> findPackagePickupTimeChart(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForList(
				"chartMapper.queryPickupTime", paramMap);
	}

	
}
