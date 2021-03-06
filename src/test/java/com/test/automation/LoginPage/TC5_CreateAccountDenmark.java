package com.test.automation.LoginPage;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.AddCardPageObject;
import com.test.automation.PageObjects.ConfirmAgePageObject;
import com.test.automation.PageObjects.CreateAccountPageObject;
import com.test.automation.TestBase.TestBase;
import com.test.automation.Tools.AuthCodeFromBase;
import com.test.automation.Tools.MailGenerator;

public class TC5_CreateAccountDenmark extends TestBase{

	CreateAccountPageObject capo;
	AuthCodeFromBase ac;
	MailGenerator mg;
	ConfirmAgePageObject agepo;
	AddCardPageObject acpo;
	
	@BeforeTest
	public void runBrowser() {
		loadProperties();
		init(prop.getProperty("browser"), prop.getProperty("baseURL"));
	}
	
	@Test(priority = 1)
	public void createAccountDenmark()  {
		capo = new CreateAccountPageObject(driver);
		capo.goToRegister();
		String randomNumber = capo.generateRandomPhoneNumber();
		capo.sendRandomPhoneNumber(randomNumber);
		ac = new AuthCodeFromBase();
		String authCode = ac.getAuthCode(randomNumber);
		capo.sendAuthCode(authCode);
		mg = new MailGenerator();
		String denmarkMail = mg.generateMailDenmark();
		capo.registerCredentials(denmarkMail, "Assa123//", "Fname" , "Lname", 1);//mail is random // Regions: 0-Norway, 1-Denmark, 2-Sweden, 3-Lithuania, 4-Latvia, 5-Estonia, 6-Poland //
		capo.allowExtra();
	}
	@Test(priority=2)
	public void sendInvalidAge() {
		agepo = new ConfirmAgePageObject(driver);
		agepo.sendInvalidAge();
		agepo.confirmButton.click();
		Assert.assertTrue(agepo.ageNotAllowedPopUp.isDisplayed(), "Invalid Age Accepted");
	}
	
	@Test(priority = 3)
	public void sendValidAge() throws InterruptedException {
		agepo.sendValidAge();
		agepo.confirmButton.click();
		
		
	}
	
	@Test(priority = 4)
	public void addRegularCardDenmark() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		acpo = new AddCardPageObject(driver);
		acpo.addRegularCard();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rolling-models")));
		Assert.assertEquals(driver.getCurrentUrl(), "https://test-extra2-core-stable.test.gneis.io/#/dashboard", "User not redirected to main page after registration");
	}
	
	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
