package com.test.automation.Tools;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;

	public ExcelReader(String path) {
		this.path = path;

		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	

	public String[][] getExcelData(String sheetName) {
		
		String[][] data = null;
		
		sheet = workbook.getSheet(sheetName);
		
		int totalRows = sheet.getLastRowNum()+1;
		
		int totalColumns = sheet.getRow(0).getLastCellNum();
		
		
		data = new String [totalRows-1][totalColumns];
		
		for (int i = 1; i < totalRows; i++) {
			row = sheet.getRow(i);
			
			for (int j = 0; j < totalColumns; j++) {
				cell = row.getCell(j);
				if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					data[i-1][j] = NumberToTextConverter.toText(cell.getNumericCellValue());
				}
				else
				data[i-1][j] = cell.getStringCellValue();
				
			}
		}

		return data;
	}
	
	
	
	
}
