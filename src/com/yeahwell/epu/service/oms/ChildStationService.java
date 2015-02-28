package com.yeahwell.epu.service.oms;

import java.util.List;
import java.util.Map;

import com.yeahwell.common.pagenation.Page;

/**
 * 子站点管理
 * 功能描述：对子站点进行相关操作
 * @author yeahwell19920525@gmail.com
 */
public interface ChildStationService {
    
	public boolean addChildStation(Map<String,Object> paramMap);
	
	public boolean updateChildStation(Map<String, Object> paramMap);

	public List<Map<String, Object>> findChildStationAll(Map<String,Object> paramMap);
   
	public Page findChildStationPage(Map<String,Object> paramMap);
    
    public Map<String, Object> findChildStationById(Map<String,Object> paramMap);
    
    public Map<String, Object> findChildStationByStructId(Map<String,Object> paramMap);
    
    
}
