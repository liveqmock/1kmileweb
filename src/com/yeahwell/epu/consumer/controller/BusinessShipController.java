package com.yeahwell.epu.consumer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.common.util.FacadeHelper;
import com.yeahwell.epu.consumer.model.MypackageForm;
import com.yeahwell.epu.express.enums.ExpressCompanyEnum;
import com.yeahwell.epu.oms.business.ChildStationBusiness;
import com.yeahwell.epu.oms.model.ChildStation;
import com.yeahwell.epu.web.annotations.IfNeedLogin;

@Controller
@IfNeedLogin(needLogin = false)
@RequestMapping("/business/ship")
public class BusinessShipController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(BusinessShipController.class);
	
	@Autowired
	private ChildStationBusiness childStationBusiness;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(final ModelMap modelMap) {
		logger.info("进入模拟发货页面");
		List<ChildStation> childStationList = childStationBusiness.findChildStationAll();
		modelMap.addAttribute("childStationList", childStationList);
		modelMap.addAttribute("expressCompanyList", ExpressCompanyEnum.toList());
		return "consumer/simulationShip";
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String doSend(
			@ModelAttribute(value="mypackageForm") MypackageForm mypackageForm,
			final ModelMap modelMap) {
		boolean sendResult = FacadeHelper.getExpressFacade().sendMypackage(mypackageForm.getStationId(), 
				mypackageForm.getExpressCompanyCode(),
				mypackageForm.getExpressNo(), 
				mypackageForm.getReceiveName(), 
				mypackageForm.getReceiveCellphone(), 
				mypackageForm.getReceiveEmail());
		logger.info("下单结果" + sendResult);
		return "redirect:/business/ship/index";
	}
	
	
	
	public static void main(String[] args) {
        System.out.println("invoke webservice...");     
		System.out.println(FacadeHelper.getHelloWorldFacade().sayHello("你妹"));
	}

}
