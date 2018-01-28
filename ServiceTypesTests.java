package org.tq.openmrs.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.tq.openmrs.pages.ManageServiceTypesPage;
import org.tq.openmrs.pages.OpenMRSHelper;
import org.tq.util.BaseClass;
import org.tq.util.Utility;

import jxl.read.biff.BiffException;

public class ServiceTypesTests extends BaseClass {

	@DataProvider(name="serviceNames")
	public String[][] fetchData() throws BiffException, IOException
	{
		String serviceTypes[][] = Utility.readXlsFile();
		return serviceTypes;
	}
	@Parameters({"username","password","url"})
	@BeforeMethod
	public void login(String uname,String pword,String url)
	{
		OpenMRSHelper helper = new OpenMRSHelper(driver);
		driver.navigate().to(url);
		helper.login(uname, pword);
		
	}
	@AfterMethod
	public void logout()
	{
		OpenMRSHelper helper = new OpenMRSHelper(driver);
		helper.logout();
	}
 
	@Test(priority=1,dataProvider="serviceNames")
	public void verifyServiceType(String serviceName)
	{
		 
		ManageServiceTypesPage sPage = new ManageServiceTypesPage(driver);
		boolean actual = sPage.verifyServiceType(serviceName);
		Assert.assertTrue(actual);
		 
	}
	@Parameters({"username","password"})
	@Test(priority=2,enabled=false)
	public void verifyAddServiceType(String uname,String pword)
	{
		OpenMRSHelper helper = new OpenMRSHelper(driver);
		helper.login(uname, pword);
		ManageServiceTypesPage sPage = new ManageServiceTypesPage(driver);
		boolean actual = sPage.addServiceType("Fever");
		Assert.assertTrue(actual);
		helper.logout();
	}
	@Parameters({"username","password"})
	@Test(priority=3,enabled=false)
	public void verifyDeleteServiceType(String uname,String pword) throws InterruptedException
	{
		OpenMRSHelper helper = new OpenMRSHelper(driver);
		helper.login(uname, pword);
		ManageServiceTypesPage sPage = new ManageServiceTypesPage(driver);
		boolean actual = sPage.deleteServiceType("General Medicine");
		Assert.assertTrue(actual);
		helper.logout();
	}
}
