package com.yeahwell.epu.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yeahwell.epu.common.controller.BaseController;

@Controller
@RequestMapping("/admin/data")
public class AdminDataController extends BaseController {

	private Logger logger = LoggerFactory
			.getLogger(AdminDataController.class);

	@RequestMapping(value = "/serverFiles", method = RequestMethod.GET)
	public String serverFiles(final ModelMap modelMap) {
		logger.info("进入数据管理>服务器文件管理");
		return "admin/data/serverFiles";
	}

}
