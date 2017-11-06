package com.test.automation.LoginPage;


import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.TestBase.TestBase;

public class TC1_LoginToExtra extends TestBase{

	LoginPageObject lpo;

	
	@DataProvider(name="myData")
	public String[][] dataProvider(){
		
		return getData("MyData", "Arkusz1");
		
		
	}
	
	@BeforeMethod
	public void runBrowser() {
		loadProperties();
		init(prop.getProperty("browser"), prop.getProperty("baseURL"));
	}
	
	@Test(dataProvider="myData")
	public void loginToExtra(String mail, String password, String runMode) {
		
		if(runMode.equalsIgnoreCase("N")){
			throw new SkipException("Skipping the test");
		}
		lpo = new LoginPageObject(driver);
		lpo.logInWithCredentials(mail, password);
		lpo.verifySuccessfulLogin();
		getScreenshot(mail);
		
	}
	
	@AfterMethod
	public void quitBrowser() {
		
		driver.quit();
	}
}
