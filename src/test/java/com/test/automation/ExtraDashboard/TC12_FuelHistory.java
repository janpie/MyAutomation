package com.test.automation.ExtraDashboard;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.ExtraDashboardPageObject;
import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.PageObjects.MyTransactionsPageObject;
import com.test.automation.TestBase.TestBase;

public class TC12_FuelHistory extends TestBase{

	LoginPageObject lpo;
	ExtraDashboardPageObject edpo;
	MyTransactionsPageObject mtpo;
	
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
	public void fuelHostoryThroughGraph() {
		edpo = new ExtraDashboardPageObject(driver);
		edpo.clickOnFuelGraph();
		WebDriverWait wait = new WebDriverWait(driver,10);
		mtpo = new MyTransactionsPageObject(driver);
		String currMonth = wait.until(ExpectedConditions.visibilityOf(mtpo.selectedMonth)).getText();
		mtpo.previousMonth();
		wait.until(ExpectedConditions.visibilityOf(mtpo.fuelAmount));
		String currMonth2 = wait.until(ExpectedConditions.visibilityOf(mtpo.selectedMonth)).getText();
		Assert.assertFalse(currMonth2.equalsIgnoreCase(currMonth), "Month not changed");
		mtpo.nextMonth();
		wait.until(ExpectedConditions.visibilityOf(mtpo.fuelAmount));
		currMonth = wait.until(ExpectedConditions.visibilityOf(mtpo.selectedMonth)).getText();
		Assert.assertFalse(currMonth2.equalsIgnoreCase(currMonth), "Month not changed");
		Assert.assertTrue(mtpo.isHeaderSelected(mtpo.fuelConsumptionHeader), "Fuel consumption header is not marked");
		Assert.assertTrue(mtpo.fuelGraph.isDisplayed(), "Fuel graph not displayed");
		
		
		
		
	}
		
		
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
