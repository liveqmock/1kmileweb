package com.yeahwell.epu.service.express.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.dao.suning.BaseDalClient;
import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.common.constants.Constants;
import com.yeahwell.epu.common.util.DateUtil;
import com.yeahwell.epu.express.enums.PackageStatusEnum;
import com.yeahwell.epu.service.express.ExpressService;

@Service
public class ExpressServiceImpl implements ExpressService {

	@Autowired
	private BaseDalClient baseDalClient;

	@Override 
	public boolean addPackage(Map<String, Object> paramMap) {
		paramMap.put("packageStatus", PackageStatusEnum.WAIT_IN_STATION.getCode());	
		int affectedRows = 0;
		affectedRows = baseDalClient.execute("packageMapper.insertPackage",
				paramMap);
		return (affectedRows > 0) ? true : false;
	}

	@Override
	public boolean updatePackageInStation(Map<String, Object> paramMap) {
	    paramMap.put("packageStatus", PackageStatusEnum.WAIT_PICKUP.getCode());
	    Date inTime = DateUtil.getCurrentTime();
	    paramMap.put("inTime", DateUtil.getCurrentTime());
	    Date outEndTime = DateUtil.addDay(inTime, Constants.PACKAGE_KEEP_DAY);
	    paramMap.put("outEndTime", outEndTime);
	    int affectedRows = 0;
	    affectedRows = baseDalClient.execute("packageMapper.updatePackageInStation", paramMap);
	    return (affectedRows > 0) ? true : false;
	}

	@Override
	public boolean updatePackageOutStation(Map<String, Object> paramMap) {
	    int affectedRows = 0;
	    Date outTime = DateUtil.getCurrentTime();
	    paramMap.put("outTime", outTime);
	    paramMap.put("packageStatus", PackageStatusEnum.TRANSACTION_SUCCESS.getCode());
	    affectedRows = baseDalClient.execute("packageMapper.updatePackageOutStation", paramMap);
	    return (affectedRows > 0) ? true : false;
	}
	
	@Override
    public boolean updatePackageEmailIncr(Map<String,Object> paramMap){
		 int affectedRows = 0;
		 affectedRows = baseDalClient.execute("packageMapper.updatePackageEmailIncr", paramMap);
		 return (affectedRows > 0) ? true : false;
	}
    
	@Override
    public boolean updatePackageMobileIncr(Map<String,Object> paramMap){
		 int affectedRows = 0;
		 affectedRows = baseDalClient.execute("packageMapper.updatePackageMobileIncr", paramMap);
		 return (affectedRows > 0) ? true : false;
	}
    
	@Override
    public boolean updatePackageStatus(Map<String,Object> paramMap){
		 int affectedRows = 0;
		 affectedRows = baseDalClient.execute("packageMapper.updatePackageStatus", paramMap);
		 return (affectedRows > 0) ? true : false;
	}
	
	@Override
	public void updatePackageAutoClose(Map<String,Object> paramMap){
		baseDalClient.execute("packageMapper.updatePackageAutoClose", paramMap);
	}
	
	@Override
	public Map<String, Object> findPackageById(
			Map<String, Object> paramMap) {
		Map<String, Object> mypackageMap = new HashMap<String, Object>();
		mypackageMap = baseDalClient.queryForMap("packageMapper.queryPackageById", paramMap);
//		Date inTime = (Date)mypackageMap.get("inTime");
//		Date outTime = (Date)mypackageMap.get("outTime");
//		Date outEndTime = (Date)mypackageMap.get("outEndTime");
//		if(null != inTime){
//			mypackageMap.put("inTime", DateUtil.formatDateTime1(inTime));
//		}
//		if(null != outTime){
//			mypackageMap.put("outTime", DateUtil.formatDateTime1(outTime));
//		}
//		if(null != outEndTime){
//			mypackageMap.put("outEndTime", DateUtil.formatDateTime1(outEndTime));
//		}
		return mypackageMap;
	}
	
	@Override
	public Map<String, Object> findPackagePickupCode(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForMap("packageMapper.queryPackagePickupCode", paramMap);
	}

	@Override
	public List<Map<String, Object>> findPackageByExpressNo(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForList(
				"packageMapper.queryPackageByExpressNo", paramMap);
	}
	
	@Override
	public List<Map<String, Object>> findPackageNotice(Map<String,Object> paramMap){
		return baseDalClient.queryForList(
				"packageMapper.queryPackageNotice", paramMap);
	}
	
	@Override
	public Integer findPackageCount(Map<String, Object> paramMap){
		return baseDalClient.queryForObject("packageMapper.queryPackageCount", paramMap, Integer.class);
	}

	@Override
	public Page findPackagePage(Map<String, Object> paramMap) {
		String countSqlId = "packageMapper.queryPackageCount";
		String pageSqlId = "packageMapper.queryPackagePage";
		return baseDalClient.queryForPage(countSqlId, pageSqlId, paramMap);
	}

	@Override
	public List<Map<String, Object>> findPackageAll(Map<String,Object> paramMap){
		return baseDalClient.queryForList(
				"packageMapper.queryPackageAll", paramMap);
	}

}
