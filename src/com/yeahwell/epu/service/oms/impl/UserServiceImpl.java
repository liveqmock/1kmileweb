package com.yeahwell.epu.service.oms.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.dao.suning.BaseDalClient;
import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.common.util.DateUtil;
import com.yeahwell.epu.service.oms.UserService;
import com.yeahwell.epu.service.oms.UserStructRealService;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private BaseDalClient baseDalClient;

	@Autowired
	private UserStructRealService userStructRealService;
	
	@Override
	public boolean addUser(Map<String, Object> paramMap) {
		paramMap.put("createTime", DateUtil.getCurrentTime());
		int affectedRows = 0;
		affectedRows = baseDalClient.execute("userMapper.insertUser", paramMap);
		boolean insertUResult = (affectedRows > 0) ? true : false;
		Map<String, Object> usrParamMap = new HashMap<String, Object>();
		usrParamMap.put("corpStructId", paramMap.get("structId"));
		boolean insertUSRResult = userStructRealService
				.addUserStructReal(usrParamMap);
		return insertUResult && insertUSRResult;
	}

	@Override
	public boolean deleteUserById(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUserBatch(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserById(Map<String, Object> paramMap) {
		int affectedRows = 0;
		affectedRows = baseDalClient.execute("userMapper.updateUserById",
				paramMap);
		return (affectedRows > 0) ? true : false;
	}

	@Override
	public boolean updateUserPwdById(Map<String, Object> paramMap) {
		int affectedRows = 0;
		affectedRows = baseDalClient.execute("userMapper.updateUserPwdById",
				paramMap);
		return (affectedRows > 0) ? true : false;
	}

	@Override
	public boolean updateUserStatusById(Map<String, Object> paramMap) {
		int affectedRows = 0;
		affectedRows = baseDalClient.execute("userMapper.updateUserStatusById",
				paramMap);
		return (affectedRows > 0) ? true : false;
	}

	@Override
	public Map<String, Object> findUserLogin(Map<String, Object> paramMap) {
		return baseDalClient.queryForMap("userMapper.queryUserLogin", paramMap);
	}

	@Override
	public Map<String, Object> findUserById(Map<String, Object> paramMap) {
		return baseDalClient.queryForMap("userMapper.queryUserById", paramMap);
	}

	@Override
	public List<Map<String, Object>> findUserByStructId(
			Map<String, Object> paramMap) {
		return baseDalClient.queryForList("userMapper.queryUserByStructId",
				paramMap);
	}

	@Override
	public Page findUserPage(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
