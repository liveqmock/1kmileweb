package com.yeahwell.epu.merchat.controller;

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

import com.yeahwell.epu.admin.model.SessionUser;
import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.merchat.form.MerchantUserLoginForm;
import com.yeahwell.epu.oms.business.UserBusiness;
import com.yeahwell.epu.oms.enums.UserTypeEnum;
import com.yeahwell.epu.web.annotations.IfNeedLogin;
import com.yeahwell.epu.web.annotations.PostHandler;

@Controller
@IfNeedLogin(needLogin = false)
@RequestMapping("/merchant")
public class MerchantSessionController extends BaseController {

	private Logger logger = LoggerFactory
			.getLogger(MerchantSessionController.class);

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
		return "merchant/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(
			@RequestParam(value = "returnURL", required = false) final String returnURL,
			final ModelMap modelMap) {
		modelMap.addAttribute("returnURL", returnURL);
		return "merchant/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(
			@ModelAttribute(value = "merchantUserLoginForm") final MerchantUserLoginForm merchantUserLoginForm,
			final ModelMap modelMap) {
		final SessionUser sessionUser = userBusiness.accountLogin(
				merchantUserLoginForm.getAccount(),
				merchantUserLoginForm.getPassword());
		// 登陆失败
		if (null == sessionUser || null == sessionUser.getUserId()) {
			modelMap.addAttribute("message", "用户名或密码错误");
			return "merchant/login";
		}
		//只有站点管理员可以登陆
		if (!UserTypeEnum.CHILD_STATION.getCode().equals(sessionUser.getType())) {
			modelMap.addAttribute("message", "用户名或密码错误");
			return "merchant/login";
		}
		// 登陆成功
		loginSession(sessionUser);
		final String returnURL = getRequest().getParameter("returnURL");
		if (StringUtils.isNotBlank(returnURL)) {
			return String.format("redirect:%s", returnURL);
		}
		return "redirect:/merchant/index";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(final ModelMap modelMap) {
		logoutSession();
		return "merchant/login";
	}

}
