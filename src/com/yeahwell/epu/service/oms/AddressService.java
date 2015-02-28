package com.yeahwell.epu.service.oms;

import java.util.List;
import java.util.Map;

public interface AddressService {
    
	public List<Map<String, Object>> listAddressAll(Map<String,Object> paramMap);

	/**
     * 查询所有省的信息
     * @param paramMap
     * @return
     */
	public List<Map<String, Object>> listProvinces(Map<String,Object> paramMap);
	
	/**
	 * 根据省编码查询省下所有市的信息
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> listCitys(Map<String,Object> paramMap);
	
	/**
	 * 根据市编码查询市下的所有区的信息
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> listDistricts(Map<String,Object> paramMap);
	
	/**
	 * 根据区编码查询区下所有街道的信息
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> listStreets(Map<String,Object> paramMap);
	
	public Map<String, Object> findProvince(Map<String,Object> paramMap);
	
	public Map<String, Object> findCity(Map<String,Object> paramMap);
	
	public Map<String, Object> findDistrict(Map<String,Object> paramMap);
	
}
