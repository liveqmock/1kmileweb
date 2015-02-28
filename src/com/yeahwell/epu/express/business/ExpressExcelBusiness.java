package com.yeahwell.epu.express.business;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.epu.express.model.Mypackage;

@Service
public class ExpressExcelBusiness {
	
	@Autowired
	private ExpressBusiness expressBusiness;

	public void exportTransaction(Integer stationId, String packageStatus, OutputStream outputStream) throws IOException{
	    HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("交易记录");
	    HSSFRow row0 = sheet.createRow(0);
	    row0.createCell(0).setCellValue("交易编码");
	    row0.createCell(1).setCellValue("运单号");
	    row0.createCell(2).setCellValue("物流公司");
	    row0.createCell(3).setCellValue("收件人");
	    row0.createCell(4).setCellValue("入站时间");
	    row0.createCell(5).setCellValue("提货时间");
	    row0.createCell(6).setCellValue("交易状态");
	    HSSFRow row = null;
	    List<Mypackage> mypackageList = expressBusiness.findPackageAll(stationId, packageStatus);
	    Mypackage mypackage = null;
	    for(int i = 0; i < mypackageList.size(); i++){
	    	row = sheet.createRow(i + 1);
	    	mypackage = mypackageList.get(i);
	    	row.createCell(0).setCellValue(mypackage.getPackageId());
	    	row.createCell(1).setCellValue(mypackage.getExpressNo());
	    	row.createCell(2).setCellValue(mypackage.getExpressCompanyCodeDesc());
	    	row.createCell(3).setCellValue(mypackage.getReceiveName());
	    	if(null != mypackage.getInTime()){
	    		row.createCell(4).setCellValue(mypackage.getInTime());
	    	}else{
	    		row.createCell(4).setCellValue("--");
	    	}
	    	if(null != mypackage.getOutTime()){
	    		row.createCell(5).setCellValue(mypackage.getOutTime());
	    	}else{
	    		row.createCell(5).setCellValue("--");
	    	}
	    	row.createCell(6).setCellValue(mypackage.getPackageStatusDesc());
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
	
}
