package com.yeahwell.epu.merchat.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.yeahwell.epu.common.model.OperatorResult;
import com.yeahwell.epu.express.business.ExpressBusiness;
import com.yeahwell.epu.express.business.ExpressNoticeBusiness;
import com.yeahwell.epu.express.enums.ExpressCompanyEnum;
import com.yeahwell.epu.express.enums.PackageStatusEnum;
import com.yeahwell.epu.express.model.Mypackage;

@Controller
@RequestMapping("/merchant/receiveGoods")
public class MerchantReceiveGoodsController extends BaseController {

	private Logger logger = LoggerFactory
			.getLogger(MerchantReceiveGoodsController.class);

	@Autowired
	private ExpressBusiness expressBusiness;

	@Autowired
	private ExpressNoticeBusiness expressNoticeBusiness;

	@ResponseBody
	@RequestMapping(value = "/listWaitInStation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page listWaitInStation(
			@RequestParam(value = STR_PAGE_NUMBER, defaultValue = PAGE_NUMBER_ONE, required = false) Integer pageNumber,
			@RequestParam(value = "expressNo", required = false) String expressNo,
			@RequestParam(value = "expressCompanyCode", required = false) String expressCompanyCode,
			@RequestParam(value = "receiveCellphone", required = false) String receiveCellphone,
			final ModelMap modelMap) {
		SessionUser sessionUser = getSessionCustomer();
		final Integer stationId = sessionUser.getStationId();
		return expressBusiness.findPackagePage(pageNumber, PAGE_SIZE,
				stationId, null, PackageStatusEnum.WAIT_IN_STATION.getCode(),
				expressNo, expressCompanyCode, null, receiveCellphone);
	}

	@RequestMapping(value = "/inStation", method = RequestMethod.GET)
	public String inStation(final ModelMap modelMap) {
		modelMap.addAttribute("expressCompanyList", ExpressCompanyEnum.toList());
		return "merchant/receiveGoods/inStation";
	}

	@ResponseBody
	@RequestMapping(value = "/inStation/{expressNo}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public OperatorResult doInStation(
			@PathVariable(value = "expressNo") String expressNo,
			final ModelMap modelMap) {
		SessionUser sessionUser = getSessionCustomer();
		final Integer stationId = sessionUser.getStationId();
		OperatorResult or = new OperatorResult();
		// 根据快递单号查询包裹信息
		List<Mypackage> mypackageList = expressBusiness
				.findPackageByExpressNo(expressNo);
		if (null == mypackageList || 0 == mypackageList.size()) {
			or.setFlag("false");
			or.setMessage("入站失败,未找到此包裹信息");
		} else if (mypackageList.size() > 1) {
			or.setFlag("false");
			or.setMessage("入站失败,存在快递单号相同的包裹，请选择要入站的包裹，并点击批量入站");
		} else {
			Mypackage mypackage = mypackageList.get(0);
			if (!PackageStatusEnum.WAIT_IN_STATION.getCode().equals(
					mypackage.getPackageStatus())) {
				or.setFlag("false");
				or.setMessage(String.format("%s入站失败,此包裹已入站", expressNo));
				return or;
			}
			boolean inStationResult = expressBusiness.inStation(mypackage
					.getPackageId());
			if (inStationResult) {
//				String pickupCode = expressBusiness
//						.findPackagePickupCode(mypackage.getPackageId());
//				Mypackage mypackageAfterInstation = expressBusiness.findPackageById(mypackage.getPackageId());
				// 发送短信 TODO delete
//				expressNoticeBusiness.sendNoticeMobileSingle(mypackageAfterInstation, pickupCode);
				// 发送邮件 TODO delete
//				expressNoticeBusiness.sendNoticeMailSingle(mypackageAfterInstation, pickupCode);
				or.setFlag("true");
				or.setMessage(String.format("%s入站成功", expressNo));
			} else {
				or.setFlag("false");
				or.setMessage(String.format("%s入站失败", expressNo));
			}
		}
		logger.info("入站结果: {}", or.toString());
		return or;
	}

	@RequestMapping(value = "/pickupGoods", method = RequestMethod.GET)
	public String pickupGoods(
			@RequestParam(value = STR_PAGE_NUMBER, defaultValue = PAGE_NUMBER_ONE, required = false) Integer pageNumber,
			final ModelMap modelMap) {
		SessionUser sessionUser = getSessionCustomer();
		final Integer stationId = sessionUser.getStationId();
		Page page = expressBusiness.findPackagePage(pageNumber, PAGE_SIZE,
				stationId, null, PackageStatusEnum.WAIT_PICKUP.getCode(), null,
				null, null, null);
		modelMap.addAttribute("page", page);
		return "merchant/receiveGoods/pickupGoods";
	}

	@RequestMapping(value = "/pickupGoodsSearch", method = RequestMethod.POST)
	public String pickupGoodsSearch(
			@RequestParam(value = "expressNo", required = false) String expressNo,
			@RequestParam(value = "receiveName", required = false) String receiveName,
			@RequestParam(value = "receiveCellphone", required = false) String receiveCellphone,
			final ModelMap modelMap) {
		SessionUser sessionUser = getSessionCustomer();
		final Integer stationId = sessionUser.getStationId();
		Page page = expressBusiness.findPackagePage(PAGE_NUMBER, PAGE_SIZE,
				stationId, null, PackageStatusEnum.WAIT_PICKUP.getCode(),
				expressNo, null, receiveName, receiveCellphone);
		modelMap.addAttribute("page", page);
		return "merchant/receiveGoods/pickupGoods";
	}

	@ResponseBody
	@RequestMapping(value = "/pickupGoods", method = RequestMethod.POST)
	public OperatorResult doPickupGoods(
			@RequestParam(value = "packageId") Integer packageId,
			@RequestParam(value = "pickupCode") String pickupCode,
			final ModelMap modelMap) {
		OperatorResult or = new OperatorResult();
		SessionUser sessionUser = getSessionCustomer();
		final Integer stationId = sessionUser.getStationId();
		if (StringUtils.isBlank(pickupCode)) {
			or.setFlag("false");
			or.setMessage("提货码不能为空");
			return or;
		} else {
			boolean flag = expressBusiness.pickupGoods(packageId, stationId,
					pickupCode);
			if (flag) {
				logger.info("{}提货成功", packageId);
				or.setFlag("true");
				or.setMessage("提货成功");
			} else {
				logger.info("{}提货失败", packageId);
				or.setFlag("false");
				or.setMessage("提货失败");
			}
		}
		return or;
	}

	@RequestMapping("/overtimeReturn")
	public String overtime(final ModelMap modelMap) {
		return "merchant/receiveGoods/overtimeReturn";
	}

}
