package com.yeahwell.epu.oms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.epu.common.util.BeanUtil;
import com.yeahwell.epu.express.enums.PackageStatusEnum;
import com.yeahwell.epu.oms.model.ChartInStationTime;
import com.yeahwell.epu.oms.model.ChartPickupTime;
import com.yeahwell.epu.oms.model.ChartProvince;
import com.yeahwell.epu.service.oms.ChartService;

@Service
public class ChartBusiness {
	
	private Logger logger = LoggerFactory.getLogger(ChartBusiness.class);

	@Autowired
	private ChartService chartService;

	public List<ChartProvince> findChartProvinceOfAdmin() {
		List<ChartProvince> chartProvinceList = new ArrayList<ChartProvince>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Map<String, Object>> provinceMapList = chartService
				.findChildStationProvinceChart(paramMap);
		ChartProvince chartProvince = null;
		for (Map<String, Object> provinceMap : provinceMapList) {
			chartProvince = new ChartProvince();
			BeanUtil.transMap2Bean(provinceMap, chartProvince);
			chartProvinceList.add(chartProvince);
		}
		return chartProvinceList;
	}
	
	public List<ChartInStationTime> findChartInStationTime(Integer stationId) {
		List<ChartInStationTime> chartInStationTimeList = new ArrayList<ChartInStationTime>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("stationId", stationId);
		paramMap.put("packageStatus", PackageStatusEnum.WAIT_PICKUP.getCode());
		List<Map<String, Object>> inTimeMapList = chartService
				.findPackageInStationTimeChart(paramMap);
		ChartInStationTime chartInStationTime = null;
		for (Map<String, Object> inTimeMap : inTimeMapList) {
			chartInStationTime = new ChartInStationTime();
			BeanUtil.transMap2Bean(inTimeMap, chartInStationTime);
			logger.debug(chartInStationTime.getInStationTime() + "," + chartInStationTime.getInStationNo());
			chartInStationTimeList.add(chartInStationTime);
		}
		return chartInStationTimeList;
	}
	
	public List<ChartPickupTime> findChartPickupTime(Integer stationId) {
		List<ChartPickupTime> chartPickupTimeList = new ArrayList<ChartPickupTime>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("stationId", stationId);
		paramMap.put("packageStatus", PackageStatusEnum.TRANSACTION_SUCCESS.getCode());
		List<Map<String, Object>> pickupMapList = chartService
				.findPackagePickupTimeChart(paramMap);
		ChartPickupTime chartPickupTime = null;
		for (Map<String, Object> pickupMap : pickupMapList) {
			chartPickupTime = new ChartPickupTime();
			BeanUtil.transMap2Bean(pickupMap, chartPickupTime);
			logger.debug(chartPickupTime.getPickupTime() + "," + chartPickupTime.getPickupNo());
			chartPickupTimeList.add(chartPickupTime);
		}
		return chartPickupTimeList;
	}

}
