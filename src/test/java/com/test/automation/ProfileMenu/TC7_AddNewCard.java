package com.test.automation.ProfileMenu;



import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.AddCardPageObject;
import com.test.automation.PageObjects.ExtraDashboardPageObject;
import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.PageObjects.MyProfilePageObject;
import com.test.automation.TestBase.TestBase;

public class TC7_AddNewCard extends TestBase{

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
	public void addNewCard() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		edpo = new ExtraDashboardPageObject(driver);
		mppo = new MyProfilePageObject(driver);
		acpo = new AddCardPageObject(driver);
		edpo.goToProfile();
		int cardListSize = wait.until(ExpectedConditions.visibilityOfAllElements(mppo.addedCardsList)).size();
		mppo.goToAddCard();
		acpo.addRegularCard();
		int cardListSizeTwo = wait.until(ExpectedConditions.visibilityOfAllElements(mppo.addedCardsList)).size();
		Assert.assertTrue(cardListSize<cardListSizeTwo, "Card not added successfully");
		
	}
	
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
