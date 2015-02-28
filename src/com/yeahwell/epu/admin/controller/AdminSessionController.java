package com.yeahwell.epu.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yeahwell.epu.admin.form.AdminUserLoginForm;
import com.yeahwell.epu.admin.model.SessionUser;
import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.oms.business.UserBusiness;
import com.yeahwell.epu.oms.enums.UserTypeEnum;
import com.yeahwell.epu.web.annotations.IfNeedLogin;
import com.yeahwell.epu.web.annotations.PostHandler;

@Controller
@RequestMapping("/admin")
@IfNeedLogin(needLogin = false)
public class AdminSessionController extends BaseController {

	private Logger logger = LoggerFactory
			.getLogger(AdminSessionController.class);
	
	private static final String CKFINDER_USERROLE = "CKFinder_UserRole";

	@Autowired
	private UserBusiness userBusiness;

	@PostHandler
	public void postHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final ModelAndView mav) throws Exception {
		if (StringUtils.isNotEmpty(request.getParameter("returnURL"))) {
			if (null != mav) {
				final String returnURL = request.getParameter("returnURL");
				mav.getModelMap().addAttribute("returnURL", returnURL);
			}
		}
	}

	@IfNeedLogin(needLogin = true)
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(final ModelMap modelMap) {
		return "admin/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "returnURL", required = false) final String returnURL,
			final ModelMap modelMap) {
		modelMap.addAttribute("returnURL", returnURL);
		return "admin/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(
			@ModelAttribute(value = "adminUserLoginForm") final AdminUserLoginForm adminUserLoginForm,
			final ModelMap modelMap) {
		final SessionUser sessionUser = userBusiness.accountLogin(
				adminUserLoginForm.getAccount(),
				adminUserLoginForm.getPassword());
		// 登陆失败
		if (null == sessionUser || null == sessionUser.getUserId()) {
			modelMap.addAttribute("message", "用户名或密码错误");
			return "admin/login";
		}
		logger.debug(sessionUser.toString() + "尝试登陆");
		// 只有后台管理员和合作商用户可以登陆
		if (!(UserTypeEnum.SUPER_ADMIN.getCode().equals(sessionUser.getType()) || UserTypeEnum.MASTER_ADMIN
				.getCode().equals(sessionUser.getType()))) {
			modelMap.addAttribute("message", "用户名或密码错误");
			return "admin/login";
		}
		if(UserTypeEnum.SUPER_ADMIN.getCode().equals(sessionUser.getType())){
			setSessionVal(CKFINDER_USERROLE, "admin");
		}
		// 登陆成功
		loginSession(sessionUser);
		final String returnURL = getRequest().getParameter("returnURL");
		if (StringUtils.isNotBlank(returnURL)) {
			return String.format("redirect:%s", returnURL);
		}
		return "redirect:/admin/index";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(final ModelMap modelMap) {
		logoutSession();
		getRequest().getSession().removeAttribute(CKFINDER_USERROLE);
		return "admin/login";
	}

}
