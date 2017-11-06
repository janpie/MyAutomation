package com.test.automation.ProfileMenu;



import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.AddCardPageObject;
import com.test.automation.PageObjects.ExtraDashboardPageObject;
import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.PageObjects.MyProfilePageObject;
import com.test.automation.TestBase.TestBase;

public class TC20_CommunicationPreferences extends TestBase{

	LoginPageObject lpo;
	ExtraDashboardPageObject edpo;
	MyProfilePageObject mppo;
	AddCardPageObject acpo;
	
	@BeforeTest
	public void runBrowser() {
		loadProperties();
		init(prop.getProperty("browser"), prop.getProperty("baseURL"));
	}
	
	@Test(priority = 1)
	public void loginToExtra() {
		lpo = new LoginPageObject(driver);
		lpo.logInWithCredentials(prop.getProperty("modifiableUser"), prop.getProperty("modifiableUserPassword"));
		lpo.verifySuccessfulLogin();
	}
	
	@Test(priority = 2)
	public void changePreferences() {
		
		edpo = new ExtraDashboardPageObject(driver);
		mppo = new MyProfilePageObject(driver);
		edpo.goToProfile();
		mppo.goToCommunicationPreferences();
		
		
		mppo.changeAndCheckCommunicationPreferences(mppo.smsTickbox, mppo.smsLabel);
		mppo.changeAndCheckCommunicationPreferences(mppo.smsTickbox, mppo.smsLabel);
		mppo.atLeastOneEnabled(mppo.smsTickbox, mppo.smsLabel);
		mppo.changeAndCheckCommunicationPreferences(mppo.emailTickbox, mppo.emailLabel);
		mppo.changeAndCheckCommunicationPreferences(mppo.emailTickbox, mppo.emailLabel);
		
		
	}
	
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
