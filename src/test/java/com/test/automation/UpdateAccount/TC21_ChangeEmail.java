package com.test.automation.UpdateAccount;

import java.util.ArrayList;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.EditProfilePageObject;
import com.test.automation.PageObjects.ExtraDashboardPageObject;
import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.PageObjects.MyProfilePageObject;
import com.test.automation.TestBase.TestBase;
import com.test.automation.Tools.GmailOperations;

public class TC21_ChangeEmail extends TestBase {

	GmailOperations gmail;
	LoginPageObject lpo;
	ExtraDashboardPageObject edpo;
	MyProfilePageObject mppo;
	EditProfilePageObject eppo;
	WebDriverWait wait;

	@BeforeTest
	public void runBrowser() {

		loadProperties();
		init(prop.getProperty("browser"), prop.getProperty("baseURL"));
		gmail = new GmailOperations(driver);
		lpo = new LoginPageObject(driver);
		edpo = new ExtraDashboardPageObject(driver);
		mppo = new MyProfilePageObject(driver);
		eppo = new EditProfilePageObject(driver);
		wait = new WebDriverWait(driver, 20);
	}

	@Test(priority = 1)
	public void loginToExtra() {
		lpo.logInWithCredentials(prop.getProperty("modifiableUser"), prop.getProperty("modifiableUserPassword"));
		lpo.verifySuccessfulLogin();
	}

	@Test(priority = 2)
	public void changeMail() {
		edpo.goToProfile();
		mppo.goToAccountUpdate();
		eppo.changeMail(prop.getProperty("mailTestCases"));
	}

	@Test(priority = 3)
	public void gmail() {
		driver.get(prop.getProperty("Gmail"));
		gmail.logInToGmail(prop.getProperty("mailTestCases"), prop.getProperty("mailTestCasesPassword"));
		gmail.getResetLink();
		gmail.verifyTranslationChangeMail(prop.getProperty("modifiableUser"));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(prop.getProperty("baseURL"));
		edpo.goToProfile();
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(mppo.mailDisplay)).getText(),
				prop.getProperty("mailTestCases"), "Mail not changed succesfully");
	}

	@Test(priority = 4)
	public void removeAccount() {
		mppo.goToAccountUpdate();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(tabs2);
		driver.switchTo().window(tabs2.get(2));
		if (prop.getProperty("modifiableUser").toLowerCase().contains("n")) {
			eppo.closeAccountWithPhrase("AVSLUTT MIN KONTO");
		} else if (prop.getProperty("modifiableUser").toLowerCase().contains("s")) {
			eppo.closeAccountWithPhrase("AVSLUTA MITT KONTO");
		} else if (prop.getProperty("modifiableUser").toLowerCase().contains("d")) {
			eppo.closeAccountWithPhrase("LUK MIN KONTO");
		}
	}

	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}

}
