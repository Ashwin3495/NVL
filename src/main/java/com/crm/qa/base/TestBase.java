package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.WebEventListener;
import com.crm.qa.util.TestUtil;


public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase() throws IOException {
		try {
			prop=new Properties();
			FileInputStream ip=new FileInputStream("C:/Users/E245979/eclipse-workspace/NVL/src/main/java/com/crm/qa/config/config.properties");
			prop.load(ip);			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void initialization() throws IOException {
	String browsername=	prop.getProperty("browser");
	
	if(browsername.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	else if(browsername.equals("FF")) {
		System.setProperty("webdriver.gecko.driver", "C:\\Tools\\chromedriver_win32\\gecko.exe");
		driver = new FirefoxDriver();
	}
	
	
	//--------Web Listener--------------
	e_driver = new EventFiringWebDriver(driver);
	// Now create object of EventListerHandler to register it with EventFiringWebDriver
	eventListener = new WebEventListener();
	e_driver.register(eventListener);
	driver = e_driver;
	
	// Window maximize
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICAIT_WAIT,TimeUnit.SECONDS);
	
	driver.get(prop.getProperty("url"));
	
	}
	
}
