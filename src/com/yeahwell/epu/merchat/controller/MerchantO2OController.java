package com.yeahwell.epu.merchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/merchant/o2o")
public class MerchantO2OController {

	@RequestMapping(value = "/salePromotion", method = RequestMethod.GET)
	public String deomCKFinder(final ModelMap modelMap) {
		return "merchant/o2o/salePromotion";
	}
	
}
