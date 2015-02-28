package com.yeahwell.epu.express.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.common.util.BeanUtil;
import com.yeahwell.epu.common.util.RandomUtil;
import com.yeahwell.epu.express.enums.ExpressCompanyEnum;
import com.yeahwell.epu.express.enums.PackageStatusEnum;
import com.yeahwell.epu.express.model.Mypackage;
import com.yeahwell.epu.service.express.ExpressService;

@Service
public class ExpressBusiness {

	@Autowired
	private ExpressService expressService;
	
	public boolean addPackage(Mypackage mypackage) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("stationId", mypackage.getStationId());
		paramMap.put("expressCompanyCode", mypackage.getExpressCompanyCode());
		paramMap.put("expressNo", mypackage.getExpressNo());
		paramMap.put("receiveName", mypackage.getReceiveName());
		paramMap.put("receiveCellphone", mypackage.getReceiveCellphone());
		paramMap.put("receiveEmail", mypackage.getReceiveEmail());
		paramMap.put("receiveFixphone", mypackage.getReceiveFixphone());
		//生成6位随机码
		paramMap.put("pickupCode", RandomUtil.getRandom(100000, 999999));
		return expressService.addPackage(paramMap);
	}
	
	public Integer findPackageCount(
			Integer stationId, String packageStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("stationId", stationId);
		if (StringUtils.isNotEmpty(packageStatus)) {
			paramMap.put("packageStatus", packageStatus);
		}
		Integer packageCount = expressService.findPackageCount(paramMap);
		return packageCount;
	}

	public Page findPackagePage(Integer pageNumber, Integer pageSize,
			Integer stationId, String packageStatus) {
		return findPackagePage(pageNumber, pageSize, stationId, null, packageStatus,
				null, null, null,null);
	}
	
	public Page findPackagePage(Integer pageNumber, Integer pageSize,
			Integer stationId, Integer packageId, String packageStatus,
			String expressNo, String expressCompanyCode, String receiveName,
			String receiveCellphone) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", pageNumber);
		paramMap.put("pageSize", pageSize);
		if (null != stationId) {
			paramMap.put("stationId", stationId);
		}
		if (null != packageId) {
			paramMap.put("packageId", packageId);
		}
		if (StringUtils.isNotEmpty(packageStatus)) {
			paramMap.put("packageStatus", packageStatus);
		}
		if (StringUtils.isNotEmpty(expressNo)) {
			paramMap.put("expressNo", expressNo);
		}
		if (StringUtils.isNotEmpty(expressCompanyCode)) {
			paramMap.put("expressCompanyCode", expressCompanyCode);
		}
		if (StringUtils.isNotEmpty(receiveName)) {
			paramMap.put("receiveName", receiveName);
		}
		if (StringUtils.isNotEmpty(receiveCellphone)) {
			paramMap.put("receiveCellphone", "%" + receiveCellphone);
		}
		Page page = expressService.findPackagePage(paramMap);
		List<Map<String, Object>> pageList = page.getPageList();
		Map<String, Object> mypackageMap = null;
		for (int i = 0; i < pageList.size(); i++) {
			mypackageMap = pageList.get(i);
			mypackageMap.put("expressCompanyCodeDesc", ExpressCompanyEnum.byCode(String.valueOf(mypackageMap.get("expressCompanyCode"))));
			mypackageMap.put("packageStatusDesc", PackageStatusEnum.byCode(String.valueOf(mypackageMap.get("packageStatus"))));
			pageList.set(i, mypackageMap);
		}
		page.setPageList(pageList);
		return page;
	}

	public List<Mypackage> findPackageByExpressNo(final String expressNo) {
		final List<Mypackage> mypackageList = new ArrayList<Mypackage>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("expressNo", expressNo);
		List<Map<String, Object>> mypackageMapList = expressService
				.findPackageByExpressNo(paramMap);
		Mypackage mypackage = null;
		for (Map<String, Object> mypackageMap : mypackageMapList) {
			mypackage = new Mypackage();
			// TODO No value specified for 'Date'异常
			BeanUtil.transMap2Bean2(mypackageMap, mypackage);
			mypackageList.add(mypackage);
		}
		return mypackageList;
	}

	public Mypackage findPackageById(final Integer packageId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageId", packageId);
		Map<String, Object> mypackageMap = expressService
				.findPackageById(paramMap);
		Mypackage mypackage = new Mypackage();
		BeanUtil.transMap2Bean2(mypackageMap, mypackage);
		return mypackage;
	}

	public boolean inStation(final Integer packageId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageId", packageId);
		return expressService.updatePackageInStation(paramMap);
	}

	public boolean pickupGoods(final Integer packageId, Integer stationId,
			String pickupCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageId", packageId);
		paramMap.put("stationId", stationId);
		paramMap.put("pickupCode", pickupCode);
		return expressService.updatePackageOutStation(paramMap);
	}
	
	public boolean sendEmailIncr(final Integer packageId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageId", packageId);
		return expressService.updatePackageEmailIncr(paramMap);
	}
	
	public boolean updatePackageStatus(final Integer packageId, String packageStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageId", packageId);
		paramMap.put("packageStatus", packageStatus);
		return expressService.updatePackageStatus(paramMap);
	}
	
	public boolean sendMobileIncr(final Integer packageId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageId", packageId);
		return expressService.updatePackageMobileIncr(paramMap);
	}
	
	public void autoClose() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageStatus", PackageStatusEnum.TRANSACTION_CLOSE.getCode());
		expressService.updatePackageAutoClose(paramMap);
	}
	
	public String findPackagePickupCode(Integer packageId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageId", packageId);
		Map<String, Object> mypackageMap = expressService.findPackagePickupCode(paramMap);
		return (String)mypackageMap.get("pickupCode");
	}
	public List<Mypackage> findPackageNoticeEmail(){
		return findPackageNotice("sendEmail");
	}
	public List<Mypackage> findPackageNoticeMobile(){
		return findPackageNotice("sendMobile");
	}
	public List<Mypackage> findPackageNoticeAgain(){
		return findPackageNotice("againNotice");
	}

	private List<Mypackage> findPackageNotice(String condition) {
		final List<Mypackage> mypackageList = new ArrayList<Mypackage>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageStatus", PackageStatusEnum.WAIT_PICKUP.getCode());
		if(StringUtils.isNotEmpty(condition)){
			paramMap.put("condition", condition);
		}
		List<Map<String, Object>> mypackageMapList = expressService
				.findPackageNotice(paramMap);
		Mypackage mypackage = null;
		for (Map<String, Object> mypackageMap : mypackageMapList) {
			mypackage = new Mypackage();
			BeanUtil.transMap2Bean2(mypackageMap, mypackage);
			mypackageList.add(mypackage);
		}
		return mypackageList;
	}
	
	public List<Mypackage> findPackageAll(Integer stationId, String packageStatus) {
		final List<Mypackage> mypackageList = new ArrayList<Mypackage>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(null != stationId){
			paramMap.put("stationId", stationId);
		}
		if(StringUtils.isNotEmpty(packageStatus)){
			paramMap.put("packageStatus", packageStatus);
		}else{
			paramMap.put("packageStatus", PackageStatusEnum.TRANSACTION_SUCCESS.getCode());
		}
		List<Map<String, Object>> mypackageMapList = expressService
				.findPackageAll(paramMap);
		Mypackage mypackage = null;
		for (Map<String, Object> mypackageMap : mypackageMapList) {
			mypackage = new Mypackage();
			BeanUtil.transMap2Bean2(mypackageMap, mypackage);
			mypackageList.add(mypackage);
		}
		return mypackageList;
	}
	
}
