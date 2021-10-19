package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {	
	
	@Test
	public void loginTest() throws IOException, InterruptedException {		
		
		//driver.get(baseURL);
		logger.info("URL is opened");
		driver.manage().window().maximize();
		
		//Thread.sleep(4000);
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);	
		logger.info("Entered username");
		
		lp.setPassword(password);		
		logger.info("Entered password");
		
		lp.clickSubmit();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);	
			logger.info("login test passed");
		}
		else
		{	
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);	
			logger.info("login test failed");
		}
		
		/*SoftAssert softassert=new SoftAssert();		
		softassert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
		softassert.assertAll();*/
		
	}
	
	

}
