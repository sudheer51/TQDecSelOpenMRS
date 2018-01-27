package org.tq.openmrs.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManageServiceTypesPage {


	WebDriver driver;
	public ManageServiceTypesPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyServiceType(String serviceName)
	{
		boolean result = false;
		List<WebElement> pagesList= driver.findElements(By.cssSelector("#appointmentTypesTable_paginate>span>a"));
		System.out.println("Number of the Pages:::: " + pagesList.size());
		counter:
			for(int i = 0;i<pagesList.size();i++)
			{
				List<WebElement> trList = driver.findElements(By.cssSelector("#appointmentTypesTable tr"));
				System.out.println("Number of table rows::" + trList.size());
				for(int j =0;j<trList.size();j++)
				{
					if(trList.get(j).getText().contains(serviceName))
					{
						System.out.println("Service Name :::" + serviceName +"  Found!!!");
						result = true;
						break counter;
					}
				}
				try{
					pagesList.get(i+1).click();
				}
				catch(Exception e)
				{
					System.out.println("in catch " + e.getMessage());
					pagesList= driver.findElements(By.cssSelector("#appointmentTypesTable_paginate>span>a"));
					pagesList.get(i+1).click();
				}
			}

		return result;
	}

	public boolean addServiceType(String serviceName)
	{
		driver.findElement(By.cssSelector("#content button")).click();
		driver.findElement(By.cssSelector("#name-field")).clear();
		driver.findElement(By.cssSelector("#name-field")).sendKeys(serviceName);
		driver.findElement(By.cssSelector("#duration-field")).clear();
		driver.findElement(By.cssSelector("#duration-field")).sendKeys("30");
		driver.findElement(By.cssSelector("#save-button")).click();
		boolean result = verifyServiceType(serviceName);
		return result;
	}
	public boolean editServiceType(String serviceName)
	{

		return true;
	}

	public boolean deleteServiceType(String serviceName) throws InterruptedException
	{

		List<WebElement> pagesList= driver.findElements(By.cssSelector("#appointmentTypesTable_paginate>span>a"));
		System.out.println("Number of the Pages:::: " + pagesList.size());
		counter:
			for(int i = 0;i<pagesList.size();i++)
			{
				List<WebElement> trList = driver.findElements(By.cssSelector("#appointmentTypesTable tr"));
				System.out.println("Number of table rows having edit icon::" + trList.size());
				for(int j =0;j<trList.size();j++)
				{
					if(trList.get(j).getText().contains(serviceName))
					{
						System.out.println("servicename is found:"+serviceName);
						driver.findElement(By.xpath("//*[@id='appointmentTypesTable']//tr["+(j+1)+"]//td[4]//i[2]")).click();
						 
						for(int k=0;k<trList.size();k++)
						{
							try{
								driver.findElement(By.xpath("(.//button[@class='confirm right'])["+(k+1)+"]")).click();
								System.out.println("Yes button is clicked::" + k);
								break;
							}
							catch(Exception e)
							{
								System.out.println("##############################################");
								System.out.println("unable to find the button:::" + e.getMessage());
								System.out.println("##############################################");
							}
						}

					}
				}
				try{
					pagesList.get(i+1).click();
				}
				catch(Exception e)
				{
					System.out.println("in catch " + e.getMessage());
					pagesList= driver.findElements(By.cssSelector("#appointmentTypesTable_paginate>span>a"));
					pagesList.get(i+1).click();
				}

			}
		boolean result = verifyServiceType(serviceName);
		return result;


	}
}