package com.test.automation.ExtraDashboard;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.ExtraDashboardPageObject;
import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.PageObjects.RollingModelsPageObject;
import com.test.automation.TestBase.TestBase;

public class TC18_BannersAndVouchers extends TestBase{

	LoginPageObject lpo;
	ExtraDashboardPageObject edpo;
	RollingModelsPageObject rmpo;
	
	@BeforeTest
	public void runBrowser() {
		loadProperties();
		init(prop.getProperty("browser"), prop.getProperty("baseURL"));
	}
	
	@Test(priority=1)
	public void loginToExtra() {
		lpo = new LoginPageObject(driver);
		lpo.logInWithCredentials(prop.getProperty("modifiableUser"), prop.getProperty("modifiableUserPassword"));
		lpo.verifySuccessfulLogin();
	}
	
	@Test(priority=2)
	public void vouchersAndBanners(){
		WebDriverWait wait = new WebDriverWait (driver,10);
		edpo = new ExtraDashboardPageObject(driver);
		wait.until(ExpectedConditions.visibilityOfAllElements(edpo.vouchers));
		Assert.assertTrue(edpo.vouchers.size()>=1, "Less than 1 vouchers displayed");
	}
		
		
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
