package com.yeahwell.epu.service.oms;

import java.util.List;
import java.util.Map;

public interface MasterStationService {
    
	public boolean addMasterStation(Map<String,Object> paramMap);
    
	public boolean updateMasterStation(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> findAllMasterStation(Map<String,Object> paramMap);
	
	public Map<String, Object> findMasterStationById(Map<String,Object> paramMap);
	
	public Map<String, Object> findMasterStationByStructId(Map<String, Object> paramMap);
    
}
