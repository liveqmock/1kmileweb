package com.yeahwell.epu.service.oms.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.dao.suning.BaseDalClient;
import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.common.util.DateUtil;
import com.yeahwell.epu.oms.enums.CorpStructTypeEnum;
import com.yeahwell.epu.service.oms.ChildStationService;
import com.yeahwell.epu.service.oms.ChildStationStructRealService;
import com.yeahwell.epu.service.oms.CorpStructService;

@Service
public class ChildStationServiceImpl implements ChildStationService {

	private Logger logger = LoggerFactory
			.getLogger(ChildStationServiceImpl.class);

	@Autowired
	private BaseDalClient baseDalClient;

	@Autowired
	private CorpStructService corpStructService;

	@Autowired
	private ChildStationStructRealService childStationStructRealService;

	@Override
	public boolean addChildStation(final Map<String, Object> paramMap) {
		int affectedRows = 0;
		affectedRows = baseDalClient.execute(
				"childStationMapper.insertChildStation", paramMap);
		boolean insertCHSResult = (affectedRows > 0) ? true : false;
		Map<String, Object> paramMapCorpStruct = new HashMap<String, Object>();
		paramMapCorpStruct.put("structName", paramMap.get("stationName"));
		paramMapCorpStruct.put("level", 1);
		paramMapCorpStruct.put("parentId", paramMap.get("structId"));
		paramMapCorpStruct.put("type",
				CorpStructTypeEnum.CHILD_STATION.getCode());
		paramMapCorpStruct.put("createTime", DateUtil.getCurrentTime());
		boolean insertCSResult = corpStructService
				.addCorpStruct(paramMapCorpStruct);

		Map<String, Object> cssrParamMap = new HashMap<String, Object>();
		boolean insertCSSRResult = childStationStructRealService
				.addChildStationStructReal(cssrParamMap);
		logger.debug(String
				.format("insertChildStation = %s, insertCorpStruct = %s, insertChildStationStructReal = %s",
						insertCHSResult, insertCSResult, insertCSSRResult));
		return insertCHSResult && insertCSResult && insertCSSRResult;
	}

	@Override
	public boolean updateChildStation(Map<String, Object> paramMap) {
		int affectedRows = 0;
		affectedRows = baseDalClient.execute(
				"childStationMapper.updateChildStation", paramMap);
		return (affectedRows > 0) ? true : false;
	}

	@Override
	public List<Map<String, Object>> findChildStationAll(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForList(
				"childStationMapper.queryChildStationAll", paramMap);
	}

	@Override
	public Page findChildStationPage(Map<String, Object> paramMap) {
		String countSqlId = "childStationMapper.queryChildStationCount";
		String pageSqlId = "childStationMapper.queryChildStationPage";
		if (null != paramMap.get("stationName")) {
			paramMap.put("stationName", "%" + paramMap.get("stationName") + "%");
		}
		return baseDalClient.queryForPage(countSqlId, pageSqlId, paramMap);
	}

	@Override
	public Map<String, Object> findChildStationById(Map<String, Object> paramMap) {
		return baseDalClient.queryForMap(
				"childStationMapper.queryChildStationById", paramMap);
	}

	@Override
	public Map<String, Object> findChildStationByStructId(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForMap(
				"childStationMapper.queryChildStationByStructId", paramMap);
	}

}
