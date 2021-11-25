package com.crm.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

import junit.framework.Assert;

public class ContactsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testutil;
	ContactPage contactsPage;
	
	String sheetName="Sheet1";
	public ContactsPageTest() throws IOException {
		// TODO Auto-generated constructor stub
		super();
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
		
		//We need to switch to frame before clicking on Contacts
		testutil.switchToframe();
		//comment the below when clicking on New contact
		//contactsPage=homePage.clickOnContactLink();  //The below method will start executing from here ie, after clicking on contact page
	}
	
	
	
	@Test(priority=1)
	public void verifyContactsPageLabel()
	{
		Assert.assertTrue("Contacts label missing",contactsPage.verifyContactsLabel());
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest()
	{
		contactsPage.selectContacts("Def JHI");

	}
	
	@Test(priority=3)
	public void selectMultiContactsTest()
	{
		contactsPage.selectContacts("Def JHI");
		contactsPage.selectContacts("Din Vij");

	}
	
	@DataProvider
	public Object[][] getTestData()
	{
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	//Never use key driven for framework
@Test(priority=4, dataProvider="getTestData")
public void validateCreateOnNewContact(String title, String firstname, String lastname, String company) throws InterruptedException
{
	Thread.sleep(2000);
	homePage.clickOnNewContactLink();
	contactsPage.createNewContact(title, firstname, lastname, company);
	
	//contactsPage.createNewContact("Dr.", "Ash", "Win", "WFT");
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
