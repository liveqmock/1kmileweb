package com.yeahwell.epu.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahwell.epu.admin.model.SessionUser;
import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.oms.business.ChildStationBusiness;
import com.yeahwell.epu.oms.business.CorpStructBusiness;
import com.yeahwell.epu.oms.business.MasterStationBusiness;
import com.yeahwell.epu.oms.enums.CorpStructTypeEnum;
import com.yeahwell.epu.oms.enums.UserTypeEnum;
import com.yeahwell.epu.oms.model.ChildStation;
import com.yeahwell.epu.oms.model.CorpStruct;
import com.yeahwell.epu.oms.model.MasterStation;

@Controller
@RequestMapping("/admin/corp")
public class AdminCorpStructController extends BaseController {

	private Logger logger = LoggerFactory
			.getLogger(AdminCorpStructController.class);

	@Autowired
	private CorpStructBusiness corpStructBusiness;
	
	@Autowired
	private MasterStationBusiness masterStationBusiness;
	
	@Autowired
	private ChildStationBusiness childStationBusiness;

	@RequestMapping(value = "/listCorpStruct", method = RequestMethod.GET)
	public String listCorpStruct(final ModelMap modelMap) {
		logger.info("进入组织结构管理");
		return "admin/corp/listCorpStruct";
	}

	@ResponseBody
	@RequestMapping(value = "/listCorpStruct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CorpStruct> listCorpMaster() {
		SessionUser sessionUser = getSessionCustomer();
		List<CorpStruct> corpStructList = null;
		if (UserTypeEnum.SUPER_ADMIN.getCode().equals(sessionUser.getType())) {
			corpStructList = corpStructBusiness.findCorpStructAll();
		} else if (UserTypeEnum.MASTER_ADMIN.getCode().equals(
				sessionUser.getType())
				|| UserTypeEnum.REGION_ADMIN.getCode().equals(
						sessionUser.getType())) {
			corpStructList = corpStructBusiness.findCorpStructOfCompany();
		} else {
			corpStructList = null;
		}
		return corpStructList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listCorpStructOfCompany", method = RequestMethod.POST)
	public List<CorpStruct> listCompany(final ModelMap modelMap) {
		//SessionUser sessionUser = getSessionCustomer();
		List<CorpStruct> corpStructList = null;
		corpStructList = corpStructBusiness.findCorpStructOfCompany();
		return corpStructList;
	}
	
	@RequestMapping(value = "/updateCorpStruct", method = RequestMethod.GET)
	public String updateCorpStruct(
			@RequestParam(value="structId") Integer structId,
			final ModelMap modelMap) {
		CorpStruct corpStruct = corpStructBusiness.findCorpStructById(structId);
		if(CorpStructTypeEnum.MASTER_STATION.getCode().equals(corpStruct.getType())){
			MasterStation masterStation = masterStationBusiness.findMasterStationByStructId(structId);
			return String.format("redirect:/admin/corp/updateMasterStation?stationId=%s", masterStation.getStationId());
		}else if(CorpStructTypeEnum.REGION_STATION.getCode().equals(corpStruct.getType())){
			
		}else if(CorpStructTypeEnum.CHILD_STATION.getCode().equals(corpStruct.getType())){
			ChildStation childStation = childStationBusiness.findChildStationByStructId(structId);
			return String.format("redirect:/admin/corp/updateChildStation?stationId=%s", childStation.getStationId());
		}else{
			
		}
		return "redirect:/admin/corp/listCorpStruct";
	}

}
