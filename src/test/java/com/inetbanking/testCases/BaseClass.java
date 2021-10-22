package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilites.ReadConfig;


public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL = readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	
	
	public WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) throws InterruptedException
	{						
		//System.setProperty("webdriver.chrome.driver","F:\\Automation\\testworkspace\\inetbankingV3\\Drivers\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver","./Drivers\\chromedriver.exe");
		
		if(br.equals("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver",readconfig.getChromepath());
			//driver = new ChromeDriver();
			
			File file = new File(readconfig.getChromepath());
		    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		    ChromeOptions options = new ChromeOptions();
		    options.addArguments("headless");
		    options.addArguments("window-size=1280x800");
		    options.addArguments("no-sandbox");
		    options.addArguments("–disable-dev-shm-usage");
		    options.addArguments("start-maximized");
		    options.addArguments("--disable-gpu");
		    options.addArguments("--disable-setuid-sandbox");
		    driver = new ChromeDriver(options);
			
			
		} else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxpath());
			driver = new FirefoxDriver();	
		} else if(br.equals("ie")) {
			System.setProperty("webdriver.ie.driver",readconfig.getIEpath());
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
		
		
		//driver.manage().window().maximize();
		
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
		
	}
	
	@AfterClass
	public void teardown() throws IOException
	{
		driver.quit();		
		//Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");			
	}
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts =  (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
		
	}
	public static String randomstring()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return generatedstring;
	}
	
	public static String randomNum() {
		String generatedstring2 = RandomStringUtils.randomNumeric(4);
		return generatedstring2;
	}

}
