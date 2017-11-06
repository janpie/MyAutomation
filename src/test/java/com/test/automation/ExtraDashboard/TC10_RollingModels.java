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

public class TC10_RollingModels extends TestBase{

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
		lpo.logInWithCredentials(prop.getProperty("fullUser"), prop.getProperty("fullUserPassword"));
		lpo.verifySuccessfulLogin();
	}
	
	@Test(priority=2)
	public void rollingModels() throws InterruptedException {
		edpo = new ExtraDashboardPageObject(driver);
		edpo.goToAnyRollingModel();
		rmpo = new RollingModelsPageObject(driver);
		WebDriverWait wait = new WebDriverWait(driver,10);
		Thread.sleep(3000);
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(rmpo.awardPicture)).isDisplayed(), "Award picture not displayed");
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(rmpo.description)).isDisplayed(), "Award description not displayed");
	}
		
		
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
