package com.test.automation.LoginPage;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.TestBase.TestBase;

public class TC16_ExtraVsKidTranslations extends TestBase {

	LoginPageObject lpo;

	@DataProvider(name = "myData")
	public String[][] dataProvider() {

		return getData("languageTest", "Arkusz1");

	}

	@Test(dataProvider = "myData")
	public void languageCheck(String browserLanguage, String account, String password, String runMode) {

		if (runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the test");
		}
		
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("acceptInsecureCerts", true);
		FirefoxOptions options = new FirefoxOptions().addPreference("intl.accept_languages", browserLanguage)
				.addCapabilities(caps);

		driver = new FirefoxDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("https://test-extra2-core-stable.sfrlabs.com/");
		lpo = new LoginPageObject(driver);

		if (browserLanguage.equals("sv")) {
			String desired = "Logga in på EXTRA CLUB med Circle K ID";
			String actual = wait.until(ExpectedConditions.visibilityOf(lpo.loginHeader)).getText();
			Assert.assertEquals(desired, actual);
		}
		else if (browserLanguage.equals("no")) {
			String desired = "Logg deg på Circle K EXTRA Club";
			String actual = wait.until(ExpectedConditions.visibilityOf(lpo.loginHeader)).getText();
			Assert.assertEquals(desired, actual);
		}
		else if (browserLanguage.equals("da")) {
			String desired = "Log ind på EXTRA CLUBmed cirkel K ID";
			String actual = wait.until(ExpectedConditions.visibilityOf(lpo.loginHeader)).getText();
			Assert.assertEquals(desired, actual);
		}
		
//		
//		lpo.logInWithCredentials(account, password);
//		lpo.verifySuccessfulLogin();
//		
//		getScreenshot(account);
//		
//		if (account.contains("dk")) {
//			System.out.println("dkaccount");
//		}
//		else if (account.contains("no")) {
//			System.out.println("noaccount");
//		}
//		else if (account.contains("se")) {
//			System.out.println("seaccount");
//		}
	}

	@AfterMethod
	public void quitBrowser() {

		driver.quit();
	}
}
