package com.yeahwell.epu.merchat.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.admin.model.SessionUser;
import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.common.util.DateUtil;
import com.yeahwell.epu.express.business.ExpressBusiness;
import com.yeahwell.epu.express.business.ExpressExcelBusiness;
import com.yeahwell.epu.express.model.Mypackage;

@Controller
@RequestMapping("/merchant/transaction")
public class MerchantTransactionController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(MerchantTransactionController.class);
	
	@Autowired
	private ExpressBusiness expressBusiness;
	
	@Autowired
	private ExpressExcelBusiness expressExcelBusiness;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listTransaction(
			@RequestParam(value = STR_PAGE_NUMBER, defaultValue = PAGE_NUMBER_ONE, required = false) Integer pageNumber,
			@RequestParam(value = "packageStatus", required = false) String packageStatus,
			final ModelMap modelMap) {
		logger.debug("进入交易记录");
		SessionUser sessionUser = getSessionCustomer();
		Page page = expressBusiness.findPackagePage(pageNumber, PAGE_SIZE, sessionUser.getStationId(), packageStatus);
		modelMap.addAttribute("page", page);
		return "merchant/transaction/listTransaction";
	}
	
	@RequestMapping(value = "/show/{packageId}", method = RequestMethod.GET)
	public String showTransaction(
			@PathVariable(value = "packageId") Integer packageId,
			final ModelMap modelMap) {
		logger.debug("进入交易详情");
		Mypackage mypackage = expressBusiness.findPackageById(packageId);
		modelMap.addAttribute("mypackage", mypackage);
		return "merchant/transaction/showTransaction";
	}
	
	@ResponseBody
	@RequestMapping(value = "/expressCount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer pickupGoodsSearch(
			@RequestParam(value = "packageStatus", required = false) String packageStatus,
			final ModelMap modelMap) {
		SessionUser sessionUser = getSessionCustomer();
		return expressBusiness.findPackageCount(sessionUser.getStationId(), packageStatus);
	}
	
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void exportTransaction(
			@RequestParam(value = "packageStatus", required = false) String packageStatus,
			final HttpServletResponse response,
			final ModelMap modelMap) throws IOException {
		logger.debug("导出交易记录");
    	response.setContentType("application/vnd.ms-excel"); 
        response.setHeader("Content-Disposition", 
        		"attachment;fileName=" + new String("交易记录".getBytes("gb2312"), "ISO8859-1") + DateUtil.formatDateTime2(new Date()) + ".xls");
        OutputStream outputStream = response.getOutputStream();
		SessionUser sessionUser = getSessionCustomer();
        expressExcelBusiness.exportTransaction(sessionUser.getStationId(), packageStatus, outputStream);
	}
	
}
