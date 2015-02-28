 package com.yeahwell.epu.merchat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yeahwell.epu.common.controller.BaseController;

@Controller
@RequestMapping("/merchant/gain")
public class MerchantGainController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(MerchantGainController.class);

	@RequestMapping(value = "/listGain", method =  RequestMethod.GET)
	public String listGain(final ModelMap modelMap) {
		return "merchant/gain/listGain";
	}
	
	@RequestMapping(value = "/showGain", method = RequestMethod.GET)
	public String showGain(final ModelMap modelMap) {
		return "merchant/gain/showGain";
	}
	
}
