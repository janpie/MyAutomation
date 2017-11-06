package com.test.automation.ProfileMenu;



import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.ExtraDashboardPageObject;
import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.PageObjects.MyProfilePageObject;
import com.test.automation.PageObjects.MyTransactionsPageObject;
import com.test.automation.TestBase.TestBase;

public class TC14_MyTransactionsThroughProfile extends TestBase{

	LoginPageObject lpo;
	ExtraDashboardPageObject edpo;
	MyProfilePageObject mppo;
	MyTransactionsPageObject mtpo;
	
	@BeforeTest
	public void runBrowser() {
		loadProperties();
		init(prop.getProperty("browser"), prop.getProperty("baseURL"));
	}
	
	@Test(priority = 1)
	public void loginToExtra() {
		lpo = new LoginPageObject(driver);
		lpo.logInWithCredentials(prop.getProperty("fullUser"), prop.getProperty("fullUserPassword"));
		lpo.verifySuccessfulLogin();
	}
	
	@Test(priority = 2)
	public void myTransactionsThroughProfile() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		edpo = new ExtraDashboardPageObject(driver);
		mppo = new MyProfilePageObject(driver);
		edpo.goToProfile();
		mppo.goToAllTransactionsThroughProfile();
		mtpo = new MyTransactionsPageObject(driver);
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
