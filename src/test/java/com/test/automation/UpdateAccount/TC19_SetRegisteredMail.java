package com.test.automation.UpdateAccount;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.CreateAccountPageObject;
import com.test.automation.PageObjects.EditProfilePageObject;
import com.test.automation.PageObjects.ExtraDashboardPageObject;
import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.PageObjects.MyProfilePageObject;
import com.test.automation.TestBase.TestBase;
import com.test.automation.Tools.AuthCodeFromBase;

public class TC19_SetRegisteredMail extends TestBase {

	LoginPageObject lpo;
	ExtraDashboardPageObject edpo;
	EditProfilePageObject eppo;
	CreateAccountPageObject capo;
	AuthCodeFromBase acfb;
	MyProfilePageObject mppo;
	public String newNumber;

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
	public void goToUpdateProfile() {
		edpo = new ExtraDashboardPageObject(driver);
		mppo = new MyProfilePageObject(driver);
		edpo.goToProfile();
		mppo.goToAccountUpdate();

	}

	@Test(priority = 3)
	public void setKnownPhoneNumber() {
		eppo = new EditProfilePageObject(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		eppo.changeMail(prop.getProperty("fullUser"));
		
		String errorMessage = wait.until(ExpectedConditions.visibilityOf(eppo.errorMessage)).getText();
		Assert.assertTrue(errorMessage.length()> 5, "Error message not displayed");
		
		

	}

	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
