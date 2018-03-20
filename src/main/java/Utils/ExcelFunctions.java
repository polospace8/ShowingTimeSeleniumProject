package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ExcelFunctions {

	 	static WebDriver driver;
	 	static XSSFWorkbook workbook;
	 	static XSSFSheet sheet;
	 	static XSSFCell cell;
	 	static String cellValue = "";
	 	static int rowNum =0;
	 	static List<Integer> list = new ArrayList<Integer>();
	 	static HashMap<String, Integer> mapObj = new HashMap<String, Integer>();
	 	static ArrayList<String> strList = new ArrayList<String>();
	 	static PropertyUtils property = new PropertyUtils("src/test/resources/config/Config.properties");
	    
	



	public static ArrayList<String> getCellValue() throws Exception {
	String strcellValue ="";	
		
	File src=new File("src/test/resources/testdata/Test.xlsx");   
	 // Load the file.
	 FileInputStream fis = new FileInputStream(src);
	 // Load he workbook.
	 workbook = new XSSFWorkbook(fis);
	 // Load the sheet in which data is stored.
	 sheet= workbook.getSheet("Sheet1");
	 for(int i=1; i<=sheet.getLastRowNum(); i++){
	 
	 // Import data for Email.
	 cell = sheet.getRow(i).getCell(0);
	 
	 if(cell.getStringCellValue().equals("Yes"))
	 {
		 rowNum = i;
		 list.add(rowNum);
		 strcellValue=GetCellValue(sheet, property.getProperty("COLUMN_NAME"), rowNum);
		 strList.add(strcellValue);
	 }
	 
	 }
	return strList;
	}
	
	public static String GetCellValue(XSSFSheet objSH,String strColumnName, int rowNum) throws Exception
	{
		
		int intXLColumn;
		String strAvailCellValue ="";
		Cell objXLCell;
		intXLColumn = FindColumnNoInExcel(objSH, strColumnName, 1);
		for (int i=0; i<list.size(); i++)
		{
			
			objXLCell = objSH.getRow(list.get(i)).getCell(intXLColumn);
			strAvailCellValue = objXLCell.getStringCellValue();
		}
		
		return strAvailCellValue;

	}
	
	public static int FindColumnNoInExcel(XSSFSheet objSH, String strColumnName, int intRowNum) throws Exception
	{
		intRowNum = intRowNum - 1;
		XSSFRow objRow = objSH.getRow(intRowNum);

		int intLastCellNum;
		String strAvailCellValue;

		try
		{
			intLastCellNum = objRow.getLastCellNum();
			for(int intCell = 0; intCell < intLastCellNum; intCell++)
			{
				strAvailCellValue = objSH.getRow(intRowNum).getCell(intCell).getStringCellValue();
				if(strAvailCellValue.equalsIgnoreCase(strColumnName))
				{
					return intCell;
				}
			}
		}
		catch(Exception e)
		{
			return -1;
		}
		return -1;
	}

	
	//get cell value for one row
	public static String getCellValueforOneRow() throws Exception {
	String strcellValue ="";	

	File src=new File("src/test/resources/testdata/Test.xlsx");   
	// Load the file.
	FileInputStream fis = new FileInputStream(src);
	// Load he workbook.
	workbook = new XSSFWorkbook(fis);
	// Load the sheet in which data is stored.
	sheet= workbook.getSheet("ShowingTimeTestData");
	for(int i=1; i<=sheet.getLastRowNum(); i++){
	 
	// Import data for Email.
	cell = sheet.getRow(i).getCell(0);
	 
	if(cell.getStringCellValue().equals("Yes"))
	{
	rowNum = i;
	strcellValue=GetCellValue(sheet, property.getProperty("COLUMN_NAME"), rowNum);
	 
	}
	 
	}
	return strcellValue;
	}
	
	
}
	 
