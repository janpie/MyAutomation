package com.test.automation.ExtraDashboard;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.ExtraDashboardPageObject;
import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.PageObjects.MyProfilePageObject;
import com.test.automation.TestBase.TestBase;

public class TC16_BrowserDesignCompatibility extends TestBase {

	LoginPageObject lpo;
	ExtraDashboardPageObject edpo;
	MyProfilePageObject mppo;

	@DataProvider(name = "myData")
	public String[][] dataProvider() {

		return getData("MyData", "Arkusz2");

	}

	@BeforeMethod
	public void runBrowser() {
		loadProperties();

	}

	@Test(dataProvider = "myData")
	public void loginToExtra(String browser, String runMode) {

		if (runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the test");
		}

		init(browser, prop.getProperty("baseURL"));
		if (browser.equalsIgnoreCase("ie")) {
			driver.get("javascript:document.getElementById('overridelink').click();");
		}

		lpo = new LoginPageObject(driver);
		lpo.logInWithCredentials(prop.getProperty("fullUser"), prop.getProperty("fullUserPassword"));
		lpo.verifySuccessfulLogin();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		edpo = new ExtraDashboardPageObject(driver);
		getScreenshot(browser + "_dashboardRM");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", edpo.goToCustomerService);
		getScreenshot(browser + "_dashboardCS");
		edpo.goToProfile();
		mppo = new MyProfilePageObject(driver);
		getScreenshot(browser + "_dashboardPN");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", mppo.goToTermsAndConditions);
		getScreenshot(browser + "_dashboardTC");
		driver.quit();
	}

	@AfterMethod
	public void quitBrowser() {

	}
}
