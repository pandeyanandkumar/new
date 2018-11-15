package com.anand.demoprj1.utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.anand.demoprj1.core.TestCore;

public class CheckRunmode extends TestCore {

	
	public static boolean isExcutable(String tcid){
		for(int rownum=2;rownum<=excel.getRowCount("test_suite");rownum++){
			if(excel.getCellData("test_suite", "tcid", rownum).equals(tcid)){
				if(excel.getCellData("test_suite", "Runmode", rownum).equalsIgnoreCase("Y")){
					return true;
				}
				else return false;
			}
			
			}
		return false;
	}
	
//----------Data provider---------------------//
      public static Object[][] getData(String sheetName){
		 
		int row = excel.getRowCount(sheetName)-1;
		if(row<=0){
			Object data[][] = new Object[1][0];
			return data;
		}
		
		int rows = excel.getRowCount(sheetName);  // Get Row Count
		int cols = excel.getColumnCount(sheetName);  // Get Col Count
		Object data[][] = new Object[rows-1][cols]; //-1
	
		
		for(int rowNum = 2 ; rowNum <= rows ; rowNum++){ //2
			
			for(int colNum=0 ; colNum< cols; colNum++){
				
				data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum); //-2
			}
		}
	
	return data;
	
	
} 
// End of data provider---------------------------
      
//Capture screenshot during tests----------------------------------------------------
      public static void captureScreenshot() {
  		
 		 Calendar cal = new GregorianCalendar();
 		  int month = cal.get(Calendar.MONTH); //4
 		  int year = cal.get(Calendar.YEAR); //2013
 		  int sec =cal.get(Calendar.SECOND);
 		  int min =cal.get(Calendar.MINUTE);
 		  int date = cal.get(Calendar.DATE);
 		  int day =cal.get(Calendar.HOUR_OF_DAY);
 				 	
 		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 	    try {
 	    	String mailscreenshotpath = System.getProperty("user.dir")+"\\screenshots\\"+year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec+".jpeg";
 			FileUtils.copyFile(scrFile, new File(mailscreenshotpath));
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}	   
 	}
	
//----------------End of capture screenshot----------------------------------//
	
}
