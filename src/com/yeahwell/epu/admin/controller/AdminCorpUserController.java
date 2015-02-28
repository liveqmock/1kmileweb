package com.yeahwell.epu.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahwell.epu.admin.form.AdminUserAddForm;
import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.oms.business.UserBusiness;
import com.yeahwell.epu.oms.model.User;

@Controller
@RequestMapping("/admin/corp")
public class AdminCorpUserController extends BaseController {

	private Logger logger = LoggerFactory
			.getLogger(AdminCorpUserController.class);

	@Autowired
	private UserBusiness userBusiness;

	@RequestMapping(value = "/listUser", method = RequestMethod.GET)
	public String listUser(final ModelMap modelMap) {
		return "admin/corp/listUser";
	}

	@ResponseBody
	@RequestMapping(value = "/listUser/{structId}", method = RequestMethod.POST)
	public List<User> listUserByStructId(
			@PathVariable(value = "structId") Integer structId,
			final ModelMap modelMap) {
		logger.info("进入用户管理");
		return userBusiness.findUserByStructId(structId);
	}

	@ResponseBody
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public Boolean doAddUser(
			@ModelAttribute(value = "userAddForm") AdminUserAddForm adminUserAddForm,
			final ModelMap modelMap) {
		boolean addResult = userBusiness.addUser(
				adminUserAddForm.getStructId(),
				adminUserAddForm.getStructType(),
				adminUserAddForm.getAccount(), adminUserAddForm.getRealName(),
				"ilove1kmile", adminUserAddForm.getEmail(),
				adminUserAddForm.getCellphone());
		return addResult;
	}

}
