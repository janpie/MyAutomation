package com.test.automation.ProfileMenu;



import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.ExtraDashboardPageObject;
import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.PageObjects.MyProfilePageObject;
import com.test.automation.PageObjects.PointsMenuPageObject;
import com.test.automation.TestBase.TestBase;

public class TC15_PointsThroughProfile extends TestBase{

	LoginPageObject lpo;
	ExtraDashboardPageObject edpo;
	MyProfilePageObject mppo;
	PointsMenuPageObject pmpo;
	
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
	public void pointsThroughProfile() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		edpo = new ExtraDashboardPageObject(driver);
		mppo = new MyProfilePageObject(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rolling-models")));
		edpo.goToProfile();
		mppo.goToPointsThroughProfile();
		pmpo = new PointsMenuPageObject(driver);
		String currMonth = wait.until(ExpectedConditions.visibilityOf(pmpo.selectedMonth)).getText();
		pmpo.previousMonth();
		String currMonth2 = wait.until(ExpectedConditions.visibilityOf(pmpo.selectedMonth)).getText();
		Assert.assertFalse(currMonth2.equalsIgnoreCase(currMonth), "Month not changed");
		pmpo.nextMonth();
		currMonth = wait.until(ExpectedConditions.visibilityOf(pmpo.selectedMonth)).getText();
		Assert.assertFalse(currMonth2.equalsIgnoreCase(currMonth), "Month not changed");
		Assert.assertTrue(pmpo.pointsEarned.isDisplayed(), "Points earned not displayed");
		Assert.assertTrue(pmpo.pointsSpent.isDisplayed(), "Points spent not displayed");
		Assert.assertTrue(pmpo.pointsExpire.isDisplayed(), "Points Expire not displayed");
		
		
	}
	
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
