package com.yeahwell.epu.excel;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class TestExcel {

	@Test
	public void testExportExcel() throws IOException{
	    HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("new sheet");

	    HSSFRow row = sheet.createRow(0);

	    HSSFCell cell = row.createCell(0);
	    cell.setCellValue(1.0D);

	    row.createCell(1).setCellValue(1.2D);
	    row.createCell(2).setCellValue("This is a string");
	    row.createCell(3).setCellValue(true);

	    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
	    wb.write(fileOut);
	    fileOut.close();
	    
	}
	
}
