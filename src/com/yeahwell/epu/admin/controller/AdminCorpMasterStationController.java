package com.yeahwell.epu.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeahwell.epu.admin.form.MasterStationForm;
import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.oms.business.MasterStationBusiness;
import com.yeahwell.epu.oms.enums.MasterStationStatusEnum;
import com.yeahwell.epu.oms.model.MasterStation;

/**
 * 合作商管理
 * @author yeahwell
 *
 */
@Controller
@RequestMapping("/admin/corp")
public class AdminCorpMasterStationController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(AdminCorpMasterStationController.class);
	
	@Autowired
	private MasterStationBusiness masterStationBusiness;
	
	@RequestMapping(value = "/listMasterStation", method = RequestMethod.GET)
	public String listMasterStation(
			final ModelMap modelMap) {
		List<MasterStation> masterStationList = masterStationBusiness.findAllMasterStation();
		modelMap.addAttribute("masterStationList", masterStationList);
		logger.info("进入合作商管理");
		return "admin/corp/listMasterStation";
	}
	
	@RequestMapping(value = "/addMasterStation", method = RequestMethod.GET)
	public String addMasterStation(
			final ModelMap modelMap) {
		modelMap.addAttribute("masterStationStatusList", MasterStationStatusEnum.toList());
		return "admin/corp/addMasterStation";
	}
	
	@RequestMapping(value = "/addMasterStation", method = RequestMethod.POST)
	public String doAddMasterStation(
			@ModelAttribute(value = "masterStationForm") MasterStationForm masterStationForm,
			final ModelMap modelMap) {
		boolean addResult = masterStationBusiness.addMasterStation(masterStationForm);
		if(addResult){
			return "redirect:/admin/corp/listMasterStation";
		}else{
			return "redirect:/admin/corp/addMasterStation";
		}
	}
	
	@RequestMapping(value = "/updateMasterStation", method = RequestMethod.GET)
	public String updateMasterStation(
			@RequestParam(value = "stationId") Integer stationId,
			final ModelMap modelMap) {
		MasterStation masterStation = masterStationBusiness.findMasterStationById(stationId);
		modelMap.addAttribute("masterStation", masterStation);
		modelMap.addAttribute("masterStationStatusList", MasterStationStatusEnum.toList());
		return "admin/corp/updateMasterStation";
	}
	
	@RequestMapping(value = "/updateMasterStation", method = RequestMethod.POST)
	public String doUpdateMasterStation(
			@ModelAttribute(value = "masterStationForm") MasterStationForm masterStationForm,
			final ModelMap modelMap) {
		boolean addResult = masterStationBusiness.updateMasterStation(masterStationForm);
		if(addResult){
			return "redirect:/admin/corp/listMasterStation";
		}else{
			return String.format("redirect:/admin/corp/listMasterStation?stationId", masterStationForm.getStationId());
		}
	}
	
}
