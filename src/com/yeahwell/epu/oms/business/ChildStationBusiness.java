package com.yeahwell.epu.oms.business;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.admin.form.ChildStationForm;
import com.yeahwell.epu.common.constants.Constants;
import com.yeahwell.epu.common.util.BeanUtil;
import com.yeahwell.epu.common.util.DateUtil;
import com.yeahwell.epu.oms.enums.ChildStationStatusEnum;
import com.yeahwell.epu.oms.enums.ChildStationTypeEnum;
import com.yeahwell.epu.oms.model.Address;
import com.yeahwell.epu.oms.model.ChildStation;
import com.yeahwell.epu.service.oms.ChildStationService;

@Service
public class ChildStationBusiness {

	private Logger logger = LoggerFactory.getLogger(ChildStationBusiness.class);

	@Autowired
	private ChildStationService childStationService;

	@Autowired
	private AddressBusiness addressBusiness;

	private String getWholeAddres(ChildStationForm childStationForm) {
		String provinceName = "";
		String cityName = "";
		String districtName = "";
		String detailAddress = childStationForm.getDetailAddress();
		Address provinceAddress = addressBusiness.findProvince(childStationForm
				.getProvinceCode());
		if (null == provinceAddress) {
			provinceName = "其他省";
		} else {
			provinceName = provinceAddress.getAddressName() + "省";
		}
		Address cityAddress = addressBusiness.findCity(childStationForm
				.getCityCode());
		if (null == cityAddress) {
			cityName = "其他市";
		} else {
			cityName = cityAddress.getAddressName();
		}
		Address districtAddress = addressBusiness.findDistrict(childStationForm
				.getDistrictCode());
		if (null == districtAddress) {
			districtName = "其他县区";
		} else {
			districtName = districtAddress.getAddressName();
		}
		return (provinceName + cityName + districtName + detailAddress);
	}

	public boolean addChildStation(ChildStationForm childStationForm) {
		Map<String, Object> paramMap = BeanUtil.transBean2Map(childStationForm);
		paramMap.put("createTime", DateUtil.getCurrentTime());
		paramMap.put("wholeAddress", getWholeAddres(childStationForm));
		return childStationService.addChildStation(paramMap);
	}

	public void importChildStation(InputStream inputStream) throws IOException {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(inputStream);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			logger.debug("Sheet {} has {} row(s)", sheet.getSheetName(), rows);
			for (int r = 0; r < rows; r++) {
				HSSFRow row = sheet.getRow(r);
				if (row == null) {
					continue;
				}
				int cells = row.getPhysicalNumberOfCells();
				logger.debug("Row {} has {} cell(s)", row.getRowNum(), cells);
				for (int c = 0; c < cells; c++) {
					HSSFCell cell = row.getCell(c);
					String value = null;
					switch (cell.getCellType()) {
					case 2:
						value = cell.getCellFormula();
						break;
					case 0:
						value = "" + cell.getNumericCellValue();
						break;
					case 1:
						value = cell.getStringCellValue();
						break;
					}
					logger.debug("CELL col={} VALUE= {}",
							cell.getColumnIndex(), value);
				}
			}
		} finally {
			if (null != inputStream) {
				inputStream.close();
			}
		}
		// ChildStationForm childStationForm = null;
		// addChildStation(childStationForm);
	}

	public boolean deleteChildStation(Integer stationId) {
		return false;
	}

	public boolean fakeDeleteChildStation(Integer stationId) {
		return false;
	}

	public boolean updateChildStation(ChildStationForm childStationForm) {
		Map<String, Object> paramMap = BeanUtil.transBean2Map(childStationForm);
		paramMap.put("wholeAddress", getWholeAddres(childStationForm));
		return childStationService.updateChildStation(paramMap);
	}

	public List<ChildStation> findChildStationAll() {
		List<ChildStation> childStationList = new ArrayList<ChildStation>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", ChildStationStatusEnum.UNLOCKED.getCode());
		List<Map<String, Object>> childStationMapList = childStationService
				.findChildStationAll(paramMap);
		ChildStation childStation = null;
		for (Map<String, Object> childStationMap : childStationMapList) {
			childStation = new ChildStation();
			BeanUtil.transMap2Bean(childStationMap, childStation);
			childStationList.add(childStation);
		}
		return childStationList;
	}

	public Page findChildStationPage(Integer structParentId, 
			Integer structId,
			Integer stationId,
			String stationName, String status, String provinceCode,
			String cityCode, String districtCode, Integer pageNumber,
			Integer pageSize) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("structParentId", structParentId);
		paramMap.put("structId", structId);
		paramMap.put("stationId", stationId);
		paramMap.put("stationName", stationName);
		paramMap.put("status", status);
		paramMap.put("provinceCode", provinceCode);
		paramMap.put("cityCode", cityCode);
		paramMap.put("districtCode", districtCode);
		if (null != pageNumber) {
			paramMap.put("pageNumber", pageNumber);
		} else {
			paramMap.put("pageNumber", Constants.PAGE_NUMBER);
		}
		if (null != pageSize) {
			paramMap.put("pageSize", pageSize);
		} else {
			paramMap.put("pageSize", Constants.PAGE_SIZE);
		}
		Page page = childStationService.findChildStationPage(paramMap);
		List<Map<String, Object>> pageList = page.getPageList();
		Map<String, Object> childStationMap = null;
		for (int i = 0; i < pageList.size(); i++) {
			childStationMap = pageList.get(i);
			childStationMap.put("typeDesc", ChildStationTypeEnum.byCode(String.valueOf(childStationMap.get("type"))));
			childStationMap.put("statusDesc", ChildStationStatusEnum.byCode(String.valueOf(childStationMap.get("status"))));
			childStationMap.put("createTime", DateUtil.formatDate1((Date)childStationMap.get("createTime")));
			pageList.set(i, childStationMap);
		}
		page.setPageList(pageList);
		return page;
	}

	public ChildStation findChildStationById(Integer stationId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("stationId", stationId);
		Map<String, Object> childStationMap = childStationService
				.findChildStationById(paramMap);
		ChildStation childStation = new ChildStation();
		BeanUtil.transMap2Bean(childStationMap, childStation);
		return childStation;
	}

	public ChildStation findChildStationByStructId(Integer structId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("structId", structId);
		Map<String, Object> childStationMap = childStationService
				.findChildStationByStructId(paramMap);
		ChildStation childStation = new ChildStation();
		BeanUtil.transMap2Bean(childStationMap, childStation);
		return childStation;
	}

}
