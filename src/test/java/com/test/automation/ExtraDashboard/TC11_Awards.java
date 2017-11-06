package com.test.automation.ExtraDashboard;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.AwardsPageObject;
import com.test.automation.PageObjects.ExtraDashboardPageObject;
import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.TestBase.TestBase;

public class TC11_Awards extends TestBase{

	LoginPageObject lpo;
	ExtraDashboardPageObject edpo;
	AwardsPageObject apo;
	
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
	public void awards() throws InterruptedException {
		edpo = new ExtraDashboardPageObject(driver);
		edpo.goToAnyAward();
		apo = new AwardsPageObject(driver);
		WebDriverWait wait = new WebDriverWait(driver,10);
		Thread.sleep(3000);
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(apo.awardPicture)).isDisplayed(), "Award picture not displayed");
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(apo.description)).isDisplayed(),"Award description not displayed");
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(apo.expirationDate)).isDisplayed(), "Award expiration date not displayed");
	
	}
		
		
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
