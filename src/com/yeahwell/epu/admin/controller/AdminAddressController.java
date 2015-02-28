package com.yeahwell.epu.admin.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahwell.epu.common.util.DateUtil;
import com.yeahwell.epu.oms.business.AddressBusiness;
import com.yeahwell.epu.oms.model.Address;

@Controller
@RequestMapping("/admin/address")
public class AdminAddressController {
	
	private Logger logger = LoggerFactory.getLogger(AdminAddressController.class);

	@Autowired
	private AddressBusiness addressBusiness;
	
	@ResponseBody
	@RequestMapping(value = "/listProvinces", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public List<Address> listProvinces() {
		return addressBusiness.listProvinces();
	}
	
	@ResponseBody
	@RequestMapping(value = "/listCitys/{provinceCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public List<Address> listCitys(@PathVariable(value = "provinceCode") String provinceCode) {
		return addressBusiness.listCitys(provinceCode);
	}
	
	@ResponseBody
	@RequestMapping(value = "/listDistricts/{cityCode}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public List<Address> listDistricts(@PathVariable(value = "cityCode") String cityCode) {
		return addressBusiness.listDistricts(cityCode);
	}
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void export(final HttpServletResponse response) throws IOException {
		logger.debug("导出交易记录");
    	response.setContentType("application/vnd.ms-excel"); 
        response.setHeader("Content-Disposition", 
        		"attachment;fileName=" + new String("地址信息模板".getBytes("gb2312"), "ISO8859-1") + DateUtil.formatDateTime2(new Date()) + ".xls");
        OutputStream outputStream = response.getOutputStream();
        addressBusiness.exportAddress(outputStream);
	}
}
