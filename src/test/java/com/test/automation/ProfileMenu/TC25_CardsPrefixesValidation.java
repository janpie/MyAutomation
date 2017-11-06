package com.test.automation.ProfileMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.automation.PageObjects.AddCardPageObject;
import com.test.automation.PageObjects.ExtraDashboardPageObject;
import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.PageObjects.MyProfilePageObject;
import com.test.automation.TestBase.TestBase;

public class TC25_CardsPrefixesValidation extends TestBase {

	LoginPageObject lpo;
	ExtraDashboardPageObject edpo;
	MyProfilePageObject mppo;
	AddCardPageObject acpo;
	WebDriverWait wait;
	SoftAssert sassert;

	@DataProvider(name = "myData")
	public String[][] dataProvider() {

		return getData("CreditCards", "Arkusz3");

	}
	
	@BeforeTest
	public void runBrowser() {
		loadProperties();
		init(prop.getProperty("browser"), prop.getProperty("baseURL"));
		lpo = new LoginPageObject(driver);
		mppo = new MyProfilePageObject(driver);
		acpo = new AddCardPageObject(driver);
		edpo = new ExtraDashboardPageObject(driver);
		wait = new WebDriverWait(driver, 10);
		sassert = new SoftAssert();
		lpo.logInWithCredentials(prop.getProperty("modifiableUser"), prop.getProperty("modifiableUserPassword"));
		lpo.verifySuccessfulLogin();
		edpo.goToProfile();

	}

	@Test(dataProvider="myData")
	public void cards(String name, String prefix, String country ) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mppo.goToAddCard();
		acpo.addEstoniaCard(prefix, "PARTNERKAART");
		
		String croppedPrefix = prefix.length() < 4 ? prefix : prefix.substring(0, 4);
		
		WebElement cardNameContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[starts-with(text(),'"+croppedPrefix+"')]//ancestor::div[1]//p[@class='default']")));
		String cardName = cardNameContainer.getText();
		cardNameContainer.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[ng-click='removeCard()']"))).click();
		
		/*try {
			Assert.assertEquals(cardName, name);
		} catch (Exception e) {
			
			e.printStackTrace();
		}*/
	}
	
	@AfterTest
	public void close() {
		driver.quit();
	
		
	}
}
