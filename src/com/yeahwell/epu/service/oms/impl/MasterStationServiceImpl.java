package com.yeahwell.epu.service.oms.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.dao.suning.BaseDalClient;
import com.yeahwell.epu.common.util.DateUtil;
import com.yeahwell.epu.oms.enums.CorpStructTypeEnum;
import com.yeahwell.epu.service.oms.CorpStructService;
import com.yeahwell.epu.service.oms.MasterStationService;
import com.yeahwell.epu.service.oms.MasterStationStructRealService;

@Service
public class MasterStationServiceImpl implements MasterStationService {

	private Logger logger = LoggerFactory
			.getLogger(MasterStationServiceImpl.class);

	@Autowired
	private BaseDalClient baseDalClient;

	@Autowired
	private CorpStructService corpStructService;

	@Autowired
	private MasterStationStructRealService masterStationStructRealService;

	@Override
	public boolean addMasterStation(final Map<String, Object> paramMap) {
		//手动处理事务
		// transactionTemplate
		// .setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		// transactionTemplate
		// .setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		// boolean insertResult = false;
		// try {
		// transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		// @Override
		// protected void doInTransactionWithoutResult(
		// TransactionStatus arg0) {
		// }
		// });
		// insertResult = true;
		// } catch (Exception e) {
		// logger.error(e.getMessage());
		// insertResult = false;
		// }
		int affectedRows = 0;
		affectedRows = baseDalClient.execute(
				"masterStationMapper.insertMasterStation", paramMap);
		boolean insertMSResult = (affectedRows > 0) ? true : false;
		Map<String, Object> paramMapCorpStruct = new HashMap<String, Object>();
		paramMapCorpStruct.put("structName", paramMap.get("stationName"));
		paramMapCorpStruct.put("level", 1);
		paramMapCorpStruct.put("parentId", 0);
		paramMapCorpStruct.put("type",
				CorpStructTypeEnum.MASTER_STATION.getCode());
		paramMapCorpStruct.put("createTime", DateUtil.getCurrentTime());
		boolean insertCSResult = corpStructService
				.addCorpStruct(paramMapCorpStruct);

		Map<String, Object> mssrParamMap = new HashMap<String, Object>();
		boolean insertMSSRResult = masterStationStructRealService
				.addMasterStationStructReal(mssrParamMap);
		return insertMSResult && insertCSResult && insertMSSRResult;

	}

	@Override
	public boolean updateMasterStation(Map<String, Object> paramMap) {
		int affectedRows = 0;
		affectedRows = baseDalClient.execute(
				"masterStationMapper.updateMasterStation", paramMap);
		return (affectedRows > 0) ? true : false;
	}

	@Override
	public List<Map<String, Object>> findAllMasterStation(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForList(
				"masterStationMapper.queryMasterStationAll", paramMap);
	}

	@Override
	public Map<String, Object> findMasterStationById(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForMap(
				"masterStationMapper.queryMasterStationById", paramMap);
	}

	@Override
	public Map<String, Object> findMasterStationByStructId(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForMap(
				"masterStationMapper.queryMasterStationByStructId", paramMap);
	}

}
