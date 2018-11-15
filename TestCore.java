package com.anand.demoprj1.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.anand.demoprj1.utils.*;

public class TestCore {
	
	/*
	 * Initialize all connections, properties etc.
	 */
     public static Properties config= new Properties();
     public static Properties object= new Properties();
     public static WebDriver driver= null;
     public static Xls_Reader excel = null;
 	 public static Logger log = Logger.getLogger("devpinoyLogger"); 
     
 	 
 	@BeforeSuite
	public static void setUp() throws IOException, AddressException, 
	ClassNotFoundException, MessagingException{
              
 		    if(driver == null){
 		    //loading Object repository file	
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\anand\\demoprj1\\properties\\object.properties");
			object.load(fis);
			log.debug("Object repository properties file loaded");
			
			//loading Configuration file
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\anand\\demoprj1\\properties\\config.properties");
			config.load(fis);
			log.debug("Configuration properties file loaded");
			
			//creating excel reader to read excel file
			excel = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\anand\\demoprj1\\properties\\testdata.xlsx");
			
			if(config.getProperty("browser").equals("firefox")){
				driver = new FirefoxDriver();
				log.debug("Opening firefox browser now");
				
			}else if(config.getProperty("browser").equals("ie")){
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.debug("Opening IE browser now");
				
			}else if(config.getProperty("browser").equals("chrome")){
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Opening Chrome browser now");
				
			}
			
			//Goto the test site URL
			driver.get(config.getProperty("testsiteurl"));
			//Implicit wait for all tests
			driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
			
			
						
		}
				
	}
			
 	  @AfterSuite
       public static void tearDown() throws AddressException, MessagingException{     
 		  
 		     log.debug("Executing After Suite");
 		     driver.quit();
	          
	         /* To-DO implement sending mail
	  		  System.out.println("Sending mail");
	  		  monitoringMail mail = new monitoringMail();
	  		  mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
               */
 		     
     }//End of tearDown method
     
     
}//End of Class file
