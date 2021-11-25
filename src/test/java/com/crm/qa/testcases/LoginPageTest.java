package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	
	//This should always be done when extending properties from base class, even before using initialize method
	public LoginPageTest() throws IOException {
		super();
		}
	
	@BeforeMethod
	public void setUp() throws IOException
	{
		initialization();
		loginPage=new LoginPage();
	}

	@Test(priority=1)
	public void loginPageTitleTest()
	{
		//Tile after login
		String title=loginPage.validateLoginPageTitle();
		Assert.assertEquals(title,"Free CRM - CRM software for customer relationship management, sales, and support.");
	}
	
	@Test(priority=2)
	public void crmLogoImageTest()
	{
		Boolean flag=loginPage.validateCRMLogo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void loginTest() throws IOException
	{
		homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
