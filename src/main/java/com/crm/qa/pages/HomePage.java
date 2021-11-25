package com.crm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage<DealsPage> extends TestBase {

	//Initialize
	public HomePage() throws IOException {
		// TODO Auto-generated constructor stub
	PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//td[contains(text(),'User: Ashwin Yadav')]")
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	public String verifyHomePageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean verifyCorrectUserName()
	{
		return userNameLabel.isDisplayed();
		
	}
	
	public ContactPage clickOnContactLink() throws IOException 
	{
		contactsLink.click();
		return new ContactPage();
	}

	public TasksPage clickOnTasksLink() throws IOException
	{
		tasksLink.click();
		return new TasksPage();
	}
	
	public DealsPage clickOnDealsLink()
	{
		dealsLink.click();
		//return new DealsPage();
		return null;
	}
	
	public void clickOnNewContactLink()
	{
		Actions action=new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContactLink.click();
	}
}
