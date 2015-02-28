package com.yeahwell.epu.merchat.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahwell.epu.admin.model.SessionUser;
import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.oms.business.ChartBusiness;
import com.yeahwell.epu.oms.model.ChartInStationTime;
import com.yeahwell.epu.oms.model.ChartPickupTime;

@Controller
@RequestMapping("/merchant/analysis")
public class MerchantAnalysisController extends BaseController {

	private Logger logger = LoggerFactory
			.getLogger(MerchantAnalysisController.class);
	
	@Autowired
	private ChartBusiness chartBusiness;

	@RequestMapping(value = "/expressTime", method = RequestMethod.GET)
	public String expressTime(
			final ModelMap modelMap) {
		return "merchant/analysis/expressTime";
	}

	@ResponseBody
	@RequestMapping(value = "/expressTime", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject analysisChildStation(){
		JSONObject json = new JSONObject();
		SessionUser sessionUser = getSessionCustomer();
		Integer stationId = sessionUser.getStationId();
		List<ChartInStationTime> inStationList = chartBusiness.findChartInStationTime(stationId);
		List<ChartPickupTime> pickupList = chartBusiness.findChartPickupTime(stationId);
		json.put("inStationList", inStationList);
		json.put("pickupList", pickupList);
		return json;
	}
	

}
