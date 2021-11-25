package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testutil;
	ContactPage contactsPage;
	
	public HomePageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeMethod
	public void setUp() throws IOException
	{
		initialization();
		contactsPage=new ContactPage();
		testutil=new TestUtil();
		loginPage=new LoginPage();
		//The below homePage helps on linking the homepage
		homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
	}

	@Test(priority=1)
	public void verifyHomePageTitleTest()
	{
		//homePage linked from above after login
		String homePageTitle=homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO","Home Page Title fail");
		//It will only print if the assert fails with below error
		//java.lang.AssertionError: Home Page Title fail expected [ CRMPRO] but found [CRMPRO]
	}
	
	@Test(priority=2)
	public void verifyUserNameTest()
	{
		testutil.switchToframe();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	@Test(priority=3)
	public void verifyContactsLinkTest() throws IOException
	{
		testutil.switchToframe();
		contactsPage=homePage.clickOnContactLink();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
