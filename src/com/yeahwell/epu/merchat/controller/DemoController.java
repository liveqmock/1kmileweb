package com.yeahwell.epu.merchat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping(value = "/demoCKFinder", method = RequestMethod.GET)
	public String deomCKFinder(final ModelMap modelMap) {
		return "demo/demoCKFinder";
	}
	
	@RequestMapping(value = "/demoMap", method = RequestMethod.GET)
	public String deomMap(final ModelMap modelMap) {
		return "demo/demoMap";
	}
}
