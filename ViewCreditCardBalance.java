package com.anand.demoprj1.tests;

import com.anand.demoprj1.core.TestCore;
import com.anand.demoprj1.utils.CheckRunmode;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ViewCreditCardBalance extends TestCore {
	
	   //Check if Executable
	    @BeforeTest
	    public void isSkip(){
	    	if(! CheckRunmode.isExcutable("ViewCreditCardBalance")){
	    		throw new SkipException("Skipping this test as run mode set to NO in excel file");
	    	}
	    }
	
	    @Test(dataProvider="getData")
	    public void viewCareditCardBalance(String card_no){
	    	  log.debug("executing View Credit card balance now");   
	    	  
	    	  try{
	    	  driver.findElement(By.xpath(object.getProperty("linkmenu"))).click();
        	  driver.findElement(By.xpath(object.getProperty("inputcrdcard"))).sendKeys(card_no);
        	  driver.findElement(By.xpath(object.getProperty("submitbutton"))).click();
        	  CheckRunmode.captureScreenshot();
        	  
	    	  }catch(Throwable t){
	    		  CheckRunmode.captureScreenshot();
		          Assert.assertTrue(false, t.getMessage());
	    	  }
	    	  
      
          }//End of method viewCreditcardBalance
          
	      @DataProvider
          public Object[][]getData(){
        	  return CheckRunmode.getData("ViewCreditCardBalance");
        	  
                  }
          
          
}//End of Class
