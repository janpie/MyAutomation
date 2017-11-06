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

public class TC13_AllTransactionsHistory extends TestBase{

	LoginPageObject lpo;
	ExtraDashboardPageObject edpo;
	MyTransactionsPageObject mtpo;
	WebDriverWait wait;
	
	@BeforeTest
	public void runBrowser() {
		loadProperties();
		init(prop.getProperty("browser"), prop.getProperty("baseURL"));
		lpo = new LoginPageObject(driver);
		edpo = new ExtraDashboardPageObject(driver);
		mtpo = new MyTransactionsPageObject(driver);
		wait = new WebDriverWait(driver,10);
	}
	
	@Test(priority=1)
	public void loginToExtra() {
		
		lpo.logInWithCredentials(prop.getProperty("fullUser"), prop.getProperty("fullUserPassword"));
		lpo.verifySuccessfulLogin();
	}
	
	@Test(priority=2)
	public void allTransactionsThroughButton() {
		
		edpo.clickOnMyTransactionsButton();
		String currMonth = wait.until(ExpectedConditions.visibilityOf(mtpo.selectedMonth)).getText();
		mtpo.previousMonth();
		wait.until(ExpectedConditions.visibilityOf(mtpo.moneyAmount));
		String currMonth2 = wait.until(ExpectedConditions.visibilityOf(mtpo.selectedMonth)).getText();
		Assert.assertFalse(currMonth2.equalsIgnoreCase(currMonth), "Month not changed");
		mtpo.nextMonth();
		wait.until(ExpectedConditions.visibilityOf(mtpo.moneyAmount));
		currMonth = wait.until(ExpectedConditions.visibilityOf(mtpo.selectedMonth)).getText();
		Assert.assertFalse(currMonth2.equalsIgnoreCase(currMonth), "Month not changed");
		
		Assert.assertTrue(mtpo.isHeaderSelected(mtpo.allTransactionsHeader), "All transactions header not selected");
		Assert.assertTrue(mtpo.transactionsMonthSummary.isDisplayed(), "Transactions summary not displayed");
		Assert.assertTrue(mtpo.allTransactionsTable.isDisplayed(), "Transactions table not displayed");
		
		
		
	}
		
		
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
