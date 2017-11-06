package com.test.automation.ExtraDashboard;


import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.ExtraDashboardPageObject;
import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.PageObjects.RollingModelsPageObject;
import com.test.automation.TestBase.TestBase;

public class TC17_CustomerServiceAccessibility extends TestBase{

	LoginPageObject lpo;
	ExtraDashboardPageObject edpo;
	RollingModelsPageObject rmpo;
	
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
	public void goToService() {
		edpo = new ExtraDashboardPageObject(driver);
		edpo.clickOnGoToCustomerService();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		String pageUrl = driver.getCurrentUrl();
		if(prop.getProperty("modifiableUser").contains("d")) {
			Assert.assertEquals(pageUrl, "https://www.circlek.dk/dk_DK/pg1332347186247/Kundeservice.html");
		
		}
		else if(prop.getProperty("modifiableUser").contains("n")) {
			Assert.assertEquals(pageUrl, "https://www.circlek.no/no_NO/pg1334073208883/private/Kundeservice.html");
		}
		
		else if(prop.getProperty("modifiableUser").contains("s")) {
			Assert.assertEquals(pageUrl, "https://www.circlek.se/sv_SE/pg1334072442377/privat/Kundservice.html");
		}
		
		else if(prop.getProperty("modifiableUser").contains("lt")) {
			Assert.assertEquals(pageUrl, "https://www.circlek.lt/lt_LT/pg1334072541909/Privatiems/klientuaptarnavimas.html");
		}
		
		else if(prop.getProperty("modifiableUser").contains("lv")) {
			Assert.assertEquals(pageUrl, "https://www.circlek.lv/lv_LV/pg1334081434489/private/Klientu-serviss.html");
		}
		
		else if(prop.getProperty("modifiableUser").contains("ee")) {
			Assert.assertEquals(pageUrl, "https://www.circlek.ee/et_EE/pg1332347172768/kontakt.html");
		}
		else if(prop.getProperty("modifiableUser").contains("pl")) {
			Assert.assertEquals(pageUrl, "https://www.circlek.pl/pl_PL/pg1334076818936/kontakt/formularze-kontaktowe/circlek-extra-club.html");
		}
		
	}
		
		
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
