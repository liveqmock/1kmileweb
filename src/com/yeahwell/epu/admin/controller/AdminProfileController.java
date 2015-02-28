package com.yeahwell.epu.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahwell.epu.admin.form.AdminUserPwdUpdateForm;
import com.yeahwell.epu.admin.form.AdminUserUpdateForm;
import com.yeahwell.epu.admin.model.SessionUser;
import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.common.model.OperatorResult;
import com.yeahwell.epu.oms.business.CorpStructBusiness;
import com.yeahwell.epu.oms.business.UserBusiness;
import com.yeahwell.epu.oms.enums.UserTypeEnum;
import com.yeahwell.epu.oms.model.User;

@Controller
@RequestMapping("/admin/profile")
public class AdminProfileController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(AdminProfileController.class);
	
	@Autowired
	private UserBusiness userBusiness;
	
	@Autowired
	private CorpStructBusiness corpStructBusiness;

	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String setting(final ModelMap modelMap) {
		logger.info("进入设置页面");
		SessionUser sessionUser = getSessionCustomer();
		User user = userBusiness.findUserById(sessionUser.getUserId());
		modelMap.addAttribute("user", user);
		if(UserTypeEnum.SUPER_ADMIN.getCode().equals(sessionUser.getType())){
			
		}else if(UserTypeEnum.MASTER_ADMIN.getCode().equals(sessionUser.getType())){
			
		}else if(UserTypeEnum.REGION_ADMIN.getCode().equals(sessionUser.getType())){
			
		}
		return "admin/profile/setting";
	}
	
	@RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
	public String accountInfo(final ModelMap modelMap) {
		SessionUser sessionUser = getSessionCustomer();
		User user = userBusiness.findUserById(sessionUser.getUserId());
		modelMap.addAttribute("user", user);
		return "admin/profile/accountInfo";
	}
	
	@ResponseBody
	@RequestMapping(value = "/accountInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	public OperatorResult updateAccountInfo(
			@ModelAttribute(value = "userUpdateForm") AdminUserUpdateForm adminUserUpdateForm,
			final ModelMap modelMap) {
		SessionUser sessionUser = getSessionCustomer();
		boolean updateResult = userBusiness.updateAdminUser(sessionUser.getUserId(), adminUserUpdateForm);
		OperatorResult or = new OperatorResult();
		or.setFlag(String.valueOf(updateResult));
		return or;
	}
	
	@RequestMapping(value = "/updatePwd", method = RequestMethod.GET)
	public String updatePwd(final ModelMap modelMap) {
		return "admin/profile/updatePwd";
	}
	
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	public String doUpdatePwd(
			@ModelAttribute(value = "userPwdUpdateForm") AdminUserPwdUpdateForm adminUserPwdUpdateForm,
			final ModelMap modelMap) {
		SessionUser sessionUser = getSessionCustomer();
		boolean updateResult = userBusiness.updateAdminUserPwd(sessionUser.getUserId(), adminUserPwdUpdateForm);
		if(updateResult){
			return "redirect:/admin/index";
		}else{
			return "redirect:/admin/profile/updatePwd";
		}
	}
	
}
