package com.yeahwell.epu.service.oms;

import java.util.List;
import java.util.Map;

public interface CorpStructService {
    
	public boolean addCorpStruct(Map<String,Object> paramMap);
	
	public Map<String, Object> findCorpStructById(Map<String,Object> paramMap);
	
	public List<Map<String, Object>> findCorpStructByParentId(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> findCorpStructAll(Map<String, Object> paramMap);
    
}
