package com.yeahwell.epu.oms.business;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.epu.common.util.BeanUtil;
import com.yeahwell.epu.oms.model.Address;
import com.yeahwell.epu.service.oms.AddressService;

@Service
public class AddressBusiness {

	@Autowired
	private AddressService addressService;

	public void exportAddress(OutputStream outputStream) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("编码");
		HSSFRow row0 = sheet.createRow(0);
		row0.createCell(0).setCellValue("名称");
		row0.createCell(1).setCellValue("父编码");
		HSSFRow row = null;
		List<Address> addressList = listAddressAll();
		Address address = null;
		for (int i = 0; i < addressList.size(); i++) {
			row = sheet.createRow(i + 1);
			address = addressList.get(i);
			row.createCell(0).setCellValue(address.getAddressCode());
			row.createCell(1).setCellValue(address.getAddressName());
			row.createCell(2).setCellValue(address.getParentCode());
		}
		try {
			wb.write(outputStream);
		} catch (IOException e) {
		} finally {
			if (null != outputStream) {
				outputStream.flush();
				outputStream.close();
			}
		}
	}

	public List<Address> listAddressAll() {
		List<Address> addressList = new ArrayList<Address>();
		List<Map<String, Object>> addressMapList = addressService
				.listAddressAll(null);
		Address address = null;
		for (Map<String, Object> addressMap : addressMapList) {
			address = new Address();
			BeanUtil.transMap2Bean(addressMap, address);
			addressList.add(address);
		}
		return addressList;
	}

	public List<Address> listProvinces() {
		List<Address> addressList = new ArrayList<Address>();
		List<Map<String, Object>> addressMapList = addressService
				.listProvinces(null);
		Address address = null;
		for (Map<String, Object> addressMap : addressMapList) {
			address = new Address();
			BeanUtil.transMap2Bean(addressMap, address);
			addressList.add(address);
		}
		return addressList;
	}

	public List<Address> listCitys(String provinceCode) {
		List<Address> addressList = new ArrayList<Address>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentCode", provinceCode);
		List<Map<String, Object>> addressMapList = addressService
				.listCitys(paramMap);
		Address address = null;
		for (Map<String, Object> addressMap : addressMapList) {
			address = new Address();
			BeanUtil.transMap2Bean(addressMap, address);
			addressList.add(address);
		}
		return addressList;
	}

	public List<Address> listDistricts(String cityCode) {
		List<Address> addressList = new ArrayList<Address>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentCode", cityCode);
		List<Map<String, Object>> addressMapList = addressService
				.listDistricts(paramMap);
		Address address = null;
		for (Map<String, Object> addressMap : addressMapList) {
			address = new Address();
			BeanUtil.transMap2Bean(addressMap, address);
			addressList.add(address);
		}
		return addressList;
	}

	public Address findProvince(String provinceCode) {
		Address address = new Address();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("addressCode", provinceCode);
		Map<String, Object> addressMap = addressService.findProvince(paramMap);
		if (null != addressMap) {
			BeanUtil.transMap2Bean(addressMap, address);
		}
		return address;
	}

	public Address findCity(String cityCode) {
		Address address = new Address();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("addressCode", cityCode);
		Map<String, Object> addressMap = addressService.findCity(paramMap);
		if (null != addressMap) {
			BeanUtil.transMap2Bean(addressMap, address);
		}
		return address;
	}

	public Address findDistrict(String districtCode) {
		Address address = new Address();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("addressCode", districtCode);
		Map<String, Object> addressMap = addressService.findDistrict(paramMap);
		if (null != addressMap) {
			BeanUtil.transMap2Bean(addressMap, address);
		}
		return address;
	}
	
	public Address findProvinceByName(String provinceName) {
		Address address = new Address();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("addressName", provinceName);
		Map<String, Object> addressMap = addressService.findProvince(paramMap);
		if (null != addressMap) {
			BeanUtil.transMap2Bean(addressMap, address);
		}
		return address;
	}
	
	public Address findCityByName(String cityName) {
		Address address = new Address();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("addressName", cityName);
		Map<String, Object> addressMap = addressService.findCity(paramMap);
		if (null != addressMap) {
			BeanUtil.transMap2Bean(addressMap, address);
		}
		return address;
	}
	
	public Address findDistrictByName(String districtName) {
		Address address = new Address();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("addressName", districtName);
		Map<String, Object> addressMap = addressService.findDistrict(paramMap);
		if (null != addressMap) {
			BeanUtil.transMap2Bean(addressMap, address);
		}
		return address;
	}

}
