package com.yeahwell.epu.service.oms;

import java.util.List;
import java.util.Map;

public interface ChartService {

	public List<Map<String, Object>> findChildStationProvinceChart(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> findPackageInStationTimeChart(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> findPackagePickupTimeChart(Map<String, Object> paramMap);

	
}
