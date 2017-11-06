package com.test.automation.LoginPage;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.TestBase.TestBase;

public class TC2_InvalidCredentials extends TestBase{

	LoginPageObject lpo;
	
	@BeforeTest
	public void runBrowser() {
		loadProperties();
		init(prop.getProperty("browser"), prop.getProperty("baseURL"));
	}
	
	@Test
	public void loginToExtra() {
		lpo = new LoginPageObject(driver);
		lpo.logInWithCredentials("invalid@credentials.com", "foobar");
		lpo.verifyInvalidLogin();
	}
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
