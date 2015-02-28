package com.yeahwell.epu.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yeahwell.epu.common.controller.BaseController;

@Controller
@RequestMapping("/admin/transaction")
public class AdminTransactionController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(AdminTransactionController.class);

	@RequestMapping(value = "/listExpressTransaction", method = RequestMethod.GET)
	public String listExpressTransaction(final ModelMap modelMap) {
		logger.info("进入物流交易管理");
		return "admin/transaction/listExpressTransaction";
	}

}
