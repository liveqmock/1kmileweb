package com.yeahwell.epu.oms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.epu.admin.form.AdminUserPwdUpdateForm;
import com.yeahwell.epu.admin.form.AdminUserUpdateForm;
import com.yeahwell.epu.admin.model.SessionUser;
import com.yeahwell.epu.common.util.BeanUtil;
import com.yeahwell.epu.oms.enums.CorpStructTypeEnum;
import com.yeahwell.epu.oms.enums.UserStatusEnum;
import com.yeahwell.epu.oms.enums.UserTypeEnum;
import com.yeahwell.epu.oms.model.CorpStruct;
import com.yeahwell.epu.oms.model.User;
import com.yeahwell.epu.service.oms.UserService;

@Service
public class UserBusiness {

	@Autowired
	private UserService userService;

	@Autowired
	private CorpStructBusiness corpStructBusiness;
	
	@Autowired
	private MasterStationBusiness masterStationBusiness;
	
	@Autowired
	private ChildStationBusiness childStationBusiness;

	private SessionUser userToSessionUser(final User userModel,
			CorpStruct corpStruct, Integer stationId) {
		final SessionUser sessionUser = new SessionUser();
		sessionUser.setUserId(userModel.getUserId());
		sessionUser.setAccount(userModel.getAccount());
		sessionUser.setRealName(userModel.getRealName());
		sessionUser.setEmail(userModel.getEmail());
		sessionUser.setCellphone(userModel.getCellphone());
		sessionUser.setStatus(userModel.getStatus());
		sessionUser.setType(userModel.getType());
		if (null != corpStruct) {
			sessionUser.setStructId(corpStruct.getStructId());
			sessionUser.setStructName(corpStruct.getStructName());
			sessionUser.setLevel(corpStruct.getLevel());
			sessionUser.setParentId(corpStruct.getParentId());
			sessionUser.setStructType(corpStruct.getType());
			sessionUser.setStationId(stationId);
		}
		return sessionUser;
	}

	/**
	 * 注册
	 */
	public void customerRegister() {

	}

	public SessionUser userLogin(String accountType, String accountValue,
			String password) {
		User user = new User();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(accountType, accountValue);
		paramMap.put("password", password);
		Map<String, Object> userMap = userService.findUserLogin(paramMap);
		BeanUtil.transMap2Bean(userMap, user);
		CorpStruct corpStruct = null;
		Integer stationId = null;
		if ((null != user.getStructId())) {
			corpStruct = corpStructBusiness.findCorpStructById(user
					.getStructId());
			if(null != corpStruct && null != corpStruct.getStructId()){
				if(CorpStructTypeEnum.MASTER_STATION.getCode().equals(corpStruct.getType())){
					stationId = masterStationBusiness.findMasterStationByStructId(corpStruct.getStructId()).getStationId();
				}else if(CorpStructTypeEnum.CHILD_STATION.getCode().equals(corpStruct.getType())){
					stationId = childStationBusiness.findChildStationByStructId(corpStruct.getStructId()).getStationId();
				}
			}
		}
		return userToSessionUser(user, corpStruct, stationId);
	}

	public SessionUser accountLogin(String account, String password) {
		return userLogin("account", account, password);
	}

	public SessionUser emailLogin(String email, String password) {
		return userLogin("email", email, password);
	}

	public SessionUser mobileLogin(String mobile, String password) {
		return userLogin("cellphone", mobile, password);
	}

	public boolean addUser(Integer structId, String structType, String account, String realName, String password, String email, String cellphone){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("structId", structId);
		if(CorpStructTypeEnum.MASTER_STATION.getCode().equals(structType)){
			paramMap.put("type", UserTypeEnum.MASTER_ADMIN.getCode());
		}else if(CorpStructTypeEnum.REGION_STATION.getCode().equals(structType)){
			paramMap.put("type", UserTypeEnum.REGION_ADMIN.getCode());
		}else if(CorpStructTypeEnum.CHILD_STATION.getCode().equals(structType)){
			paramMap.put("type", UserTypeEnum.CHILD_STATION.getCode());
		}else{
			return false;
		}
		paramMap.put("account", account);
		paramMap.put("realName", realName);
		paramMap.put("password", password);
		paramMap.put("email", email);
		paramMap.put("cellphone", cellphone);
		paramMap.put("status", UserStatusEnum.INACTIVE.getCode());
		return userService.addUser(paramMap);
	}

	public boolean updateAdminUser(Integer userId,
			AdminUserUpdateForm adminUserUpdateForm) {
		Map<String, Object> paramMap = BeanUtil
				.transBean2Map(adminUserUpdateForm);
		paramMap.put("userId", userId);
		return userService.updateUserById(paramMap);
	}

	public boolean updateAdminUserPwd(Integer userId,
			AdminUserPwdUpdateForm adminUserPwdUpdateForm) {
		Map<String, Object> paramMap = BeanUtil
				.transBean2Map(adminUserPwdUpdateForm);
		paramMap.put("userId", userId);
		return userService.updateUserPwdById(paramMap);
	}

	public User findUserById(Integer userId) {
		User user = new User();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		Map<String, Object> userMap = userService.findUserById(paramMap);
		BeanUtil.transMap2Bean(userMap, user);
		return user;
	}

	public List<User> findUserByStructId(Integer structId) {
		List<User> userList = new ArrayList<User>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("structId", structId);
		List<Map<String, Object>> userListMap = userService
				.findUserByStructId(paramMap);
		User user = null;
		for (Map<String, Object> userMap : userListMap) {
			user = new User();
			BeanUtil.transMap2Bean(userMap, user);
			userList.add(user);
		}
		return userList;
	}

	/**
	 * 安全退出
	 */
	public void customerLogout() {

	}

	/**
	 * 注销会员
	 */
	public void closeCustomer() {

	}

}
