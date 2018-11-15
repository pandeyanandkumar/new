package com.anand.demoprj1.tests;

import com.anand.demoprj1.core.TestCore;
import com.anand.demoprj1.utils.CheckRunmode;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutCart extends TestCore {
	
	   //Check if Executable
	    @BeforeTest
	    public void isSkip(){
	    	if(! CheckRunmode.isExcutable("CheckoutCart")){
	    		throw new SkipException("Skipping this as run mode set to NO");
	    	}
	    }
	
	    @Test(dataProvider="getData")
	    public void Buyobject(String qty, String card, String month, String year, String cv){
	    	  log.debug("executing buy item now");
	    	  try{
	    	  Select selectByValue = new Select(driver.findElement(By.xpath(object.getProperty("select"))));
              selectByValue.selectByValue(qty);
        	  driver.findElement(By.xpath(object.getProperty("buybutton"))).click();
        	  driver.findElement(By.xpath(object.getProperty("card"))).sendKeys(card);
        	  Select selectmonth = new Select(driver.findElement(By.xpath(object.getProperty("expmonth"))));
        	  selectmonth.selectByValue(month);
        	  Select selectyear = new Select(driver.findElement(By.xpath(object.getProperty("expyear"))));
        	  selectyear.selectByValue(year);
        	  driver.findElement(By.xpath(object.getProperty("cvv"))).sendKeys(cv);
        	  driver.findElement(By.xpath(object.getProperty("paybutton"))).click();
        	  CheckRunmode.captureScreenshot();
        	  
                }catch(Throwable t){
	                   CheckRunmode.captureScreenshot();
	                   Assert.assertTrue(false, t.getMessage());
                       }
	    	  
         }//End of BuyObject method
	    
	    @DataProvider
        public Object[][]getData(){
      	  return CheckRunmode.getData("CheckoutCart");
      	 }
	    
	    
}//End of Class
