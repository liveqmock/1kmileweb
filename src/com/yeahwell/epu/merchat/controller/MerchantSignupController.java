package com.yeahwell.epu.merchat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.web.annotations.IfNeedLogin;

@Controller
@IfNeedLogin(needLogin = false)
@RequestMapping(value = "/merchant/signup")
public class MerchantSignupController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(MerchantSignupController.class);

	@RequestMapping("/step1")
	public String index(final ModelMap modelMap) {
		return "merchant/signup/step1";
	}

}
