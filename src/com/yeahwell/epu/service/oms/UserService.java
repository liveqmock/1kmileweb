package com.yeahwell.epu.service.oms;

import java.util.List;
import java.util.Map;

import com.yeahwell.common.pagenation.Page;

public interface UserService {

	public boolean addUser(Map<String,Object> paramMap);
	
	public boolean deleteUserById(Map<String,Object> paramMap);

	public boolean deleteUserBatch(Map<String,Object> paramMap);
	
	public boolean updateUserById(Map<String, Object> paramMap);
	
	public boolean updateUserPwdById(Map<String, Object> paramMap);
	
	public boolean updateUserStatusById(Map<String, Object> paramMap);
	
	public Map<String, Object> findUserById(Map<String,Object> paramMap);
	
	public List<Map<String, Object>> findUserByStructId(Map<String,Object> paramMap);
	
	Map<String, Object> findUserLogin(Map<String, Object> paramMap);
	
    public Page findUserPage(Map<String,Object> paramMap);

    
    
}
