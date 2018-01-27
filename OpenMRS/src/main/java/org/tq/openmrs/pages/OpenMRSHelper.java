package org.tq.openmrs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenMRSHelper {

	WebDriver driver;
	public OpenMRSHelper(WebDriver driver)
	{
		System.out.println("Modifying the code for branch US1234");
		this.driver = driver;
	}
	public   void login(String uname,String pword)
	{
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pword);
		driver.findElement(By.id("Inpatient Ward")).click();
		driver.findElement(By.id("loginButton")).submit();

	}
	public   void logout()
	{

		driver.findElement(By.linkText("Logout")).click();
	}
}
