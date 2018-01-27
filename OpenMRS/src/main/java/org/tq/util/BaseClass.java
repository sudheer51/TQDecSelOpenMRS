package org.tq.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	protected WebDriver driver;

	@AfterClass
	public void afterTest()
	{
		System.out.println("in after test");
	}
	@Parameters({"browserType","url"})
	@BeforeClass
	public void invokeBrowser(String browserType,String url)
	{
		if(browserType.equals("FF"))
		{
			System.out.println("in FF");
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();

		}
		else if(browserType.equals("CH"))
		{
			System.out.println("in CH");
			System.setProperty("webdriver.chrome.driver","chromedriver.exe");
			driver = new ChromeDriver();

		}
		else
		{
			System.out.println("in IE");
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	 
	}
}
