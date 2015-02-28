package com.yeahwell.epu.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.admin.form.ChildStationForm;
import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.oms.business.AddressBusiness;
import com.yeahwell.epu.oms.business.ChildStationBusiness;
import com.yeahwell.epu.oms.business.CorpStructBusiness;
import com.yeahwell.epu.oms.enums.ChildStationStatusEnum;
import com.yeahwell.epu.oms.enums.ChildStationTypeEnum;
import com.yeahwell.epu.oms.model.ChildStation;
import com.yeahwell.epu.oms.model.CorpStruct;

/**
 * 站点管理
 * 
 * @author yeahwell
 * 
 */
@Controller
@RequestMapping("/admin/corp")
public class AdminCorpChildStationController extends BaseController {

	private Logger logger = LoggerFactory
			.getLogger(AdminCorpChildStationController.class);

	@Autowired
	private ChildStationBusiness childStationBusiness;
	
	@Autowired
	private CorpStructBusiness corpStructBusiness;

	@Autowired
	private AddressBusiness addressBusiness;

	@RequestMapping(value = "/listChildStation", method = RequestMethod.GET)
	public String listChildStation(
			@RequestParam(value = STR_PAGE_NUMBER, defaultValue = PAGE_NUMBER_ONE, required = false) Integer pageNumber,
			final ModelMap modelMap) {
		modelMap.addAttribute("provinceList", addressBusiness.listProvinces());
		return "admin/corp/listChildStation";
	}

	@ResponseBody
	@RequestMapping(value = "/listChildStation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page listChildStation(
			@RequestParam(value = STR_PAGE_NUMBER, defaultValue = PAGE_NUMBER_ONE, required = false) Integer pageNumber,
			@RequestParam(value = "structParentId", required = false) Integer structParentId,
			@RequestParam(value = "structId", required = false) Integer structId,
			@RequestParam(value = "stationId", required = false) Integer stationId,
			@RequestParam(value = "stationName", required = false) String stationName,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "provinceCode", required = false) String provinceCode,
			@RequestParam(value = "cityCode", required = false) String cityCode,
			@RequestParam(value = "districtCode", required = false) String districtCode) {
		Page page = childStationBusiness.findChildStationPage(structParentId,
				structId, stationId, stationName, status, provinceCode,
				cityCode, districtCode, pageNumber, PAGE_SIZE);
		return page;
	}

	@RequestMapping(value = "/updateChildStation", method = RequestMethod.GET)
	public String updateChildStation(
			@RequestParam(value = "stationId") Integer stationId,
			final ModelMap modelMap) {
		ChildStation childStation = childStationBusiness
				.findChildStationById(stationId);
		modelMap.addAttribute("childStation", childStation);
		modelMap.addAttribute("childStationStatusList",
				ChildStationStatusEnum.toList());
		modelMap.addAttribute("childStationTypeList",
				ChildStationTypeEnum.toList());
		CorpStruct corpStruct = corpStructBusiness.findCorpStructById(childStation.getStructId());
		CorpStruct parentCorpStruct = corpStructBusiness.findCorpStructById(corpStruct.getParentId());
		modelMap.addAttribute("parentCorpStruct", parentCorpStruct);
		modelMap.addAttribute("provinceList", addressBusiness.listProvinces());
		return "admin/corp/updateChildStation";
	}

	@RequestMapping(value = "/updateChildStation", method = RequestMethod.POST)
	public String doUpdateChildStation(
			@ModelAttribute("childStationForm") ChildStationForm childStationForm,
			final ModelMap modelMap) {
		boolean updateResult = childStationBusiness
				.updateChildStation(childStationForm);
		if (updateResult) {
			return "redirect:/admin/corp/listChildStation";
		} else {
			return String.format(
					"redirect:/admin/corp/updateChildStation?stationId=",
					childStationForm.getStationId());
		}
	}

	@RequestMapping(value = "/addChildStation", method = RequestMethod.GET)
	public String addChildStation(final ModelMap modelMap) {
		modelMap.addAttribute("childStationStatusList",
				ChildStationStatusEnum.toList());
		modelMap.addAttribute("childStationTypeList",
				ChildStationTypeEnum.toList());
		modelMap.addAttribute("provinceList", addressBusiness.listProvinces());
		return "admin/corp/addChildStation";
	}

	@RequestMapping(value = "/addChildStation", method = RequestMethod.POST)
	public String doAddChildStation(
			@ModelAttribute(value = "childStationForm") ChildStationForm childStationForm,
			final ModelMap modelMap) {
		boolean addResult = childStationBusiness
				.addChildStation(childStationForm);
		logger.info("添加子站点结果" + addResult);
		if (addResult) {
			return "redirect:/admin/corp/listChildStation";
		} else {
			return String.format("redirect:/admin/corp/addChildStation");
		}
	}

	@RequestMapping(value = "/importChildStation", method = RequestMethod.GET)
	public String importChildStation(final ModelMap modelMap) {
		return "admin/corp/importChildStation";
	}

	@RequestMapping(value = "/importChildStation", method = RequestMethod.POST)
	public String doImportChildStation(
			@RequestParam(value = "structId", required = false) Integer structId,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, final ModelMap modelMap)
			throws IOException {
		childStationBusiness.importChildStation(file.getInputStream());
		// String path = request.getSession().getServletContext()
		// .getRealPath("upload/temp");
		// logger.debug(path);
		//
		// String fileName = DateUtil.formatDateTime2(new Date())
		// + file.getOriginalFilename();
		// File targetFile = new File(path, fileName);
		// if (!targetFile.exists()) {
		// targetFile.mkdirs();
		// }
		// try {
		// file.transferTo(targetFile);
		// } catch (Exception e) {
		// logger.error(e.getMessage());
		// }
		// modelMap.addAttribute("fileUrl", request.getContextPath()
		// + "/upload/temp" + fileName);

		return "redirect:/admin/corp/listChildStation";
	}

}
