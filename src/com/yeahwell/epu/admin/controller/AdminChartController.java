package com.yeahwell.epu.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.oms.business.ChartBusiness;
import com.yeahwell.epu.oms.model.ChartProvince;

/**
 * 站点管理
 * @author yeahwell
 *
 */
@Controller
@RequestMapping("/admin/chart")
public class AdminChartController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(AdminChartController.class);
	
	@Autowired
	private ChartBusiness chartBusiness;

	@ResponseBody
	@RequestMapping(value = "/analysisChildStation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ChartProvince> analysisChildStation(){
		logger.info("生成子站点分布图");
		return chartBusiness.findChartProvinceOfAdmin();
	}
	

}
