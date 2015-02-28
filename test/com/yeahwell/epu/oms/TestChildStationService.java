package com.yeahwell.epu.oms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yeahwell.epu.base.TestBaseTemplate;
import com.yeahwell.epu.common.util.DateUtil;
import com.yeahwell.epu.common.util.RandomUtil;
import com.yeahwell.epu.oms.business.AddressBusiness;
import com.yeahwell.epu.oms.enums.ChildStationStatusEnum;
import com.yeahwell.epu.oms.enums.ChildStationTypeEnum;

public class TestChildStationService extends TestBaseTemplate {

	private static Logger logger = LoggerFactory
			.getLogger(TestChildStationService.class);
	
	@Autowired
	private AddressBusiness addressBusiness;
	
	@Test
	public void getRandom(){
		System.out.println(RandomUtil.getRandom(1, 10));
	}
	
	
	public Map<String, String> getAddressMap(){
		Map<String, String> addressMap = new HashMap<String, String>();
		String provinceCode = addressBusiness.listProvinces().get(RandomUtil.getRandom(1, 5)).getAddressCode();
		String cityCode = addressBusiness.listCitys(provinceCode).get(0).getAddressCode();
		String districtCode = addressBusiness.listDistricts(cityCode).get(0).getAddressCode();
		addressMap.put("provinceCode", provinceCode);
		addressMap.put("cityCode",  cityCode);
		addressMap.put("districtCode", districtCode);
		logger.info(addressMap.toString());
		return addressMap;
	}
	
	private List<Map<String, Object>> initPackageMap() {
		List<Map<String, Object>> childStationList = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < 100; i++) {
			Map<String, Object> childStationMapTemp = new HashMap<String, Object>();
			childStationMapTemp.put("stationName", "stationName" + i);
			childStationMapTemp.put("type", ChildStationTypeEnum.TYPE_0000.getCode());
			childStationMapTemp.put("businessTime", "周一至周五(8:00~22:00) 周六至周日(9:00~21:00)");
			childStationMapTemp.put("stationDesc", "关于stationName" + i + "的简介");
			childStationMapTemp.put("provinceCode", getAddressMap().get("provinceCode"));
			childStationMapTemp.put("cityCode", getAddressMap().get("cityCode"));
			childStationMapTemp.put("districtCode", getAddressMap().get("districtCode"));
			childStationMapTemp.put("streetCode", "----");
			childStationMapTemp.put("detailAddress", "益江路516弄北门入口");
			childStationMapTemp.put("linkman", "王老板");
			childStationMapTemp.put("cellphone", "13112345678");
			childStationMapTemp.put("fixphone", "025-3451454");
			childStationMapTemp.put("createTime", DateUtil.getCurrentTime());
            childStationMapTemp.put("status", ChildStationStatusEnum.LOCKED.getCode());			
			childStationList.add(childStationMapTemp);
		}
		return childStationList;
	}

	@Test
	public void testAddChildStation() {
		List<Map<String, Object>> childStations = initPackageMap();
		for(Map<String, Object> tempPackageMap : childStations){
//			childStationService.addChildStation(tempPackageMap);
		}
		logger.info("添加站点成功");
	}
	
}
