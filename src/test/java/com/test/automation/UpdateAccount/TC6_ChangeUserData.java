package com.test.automation.UpdateAccount;

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

public class TC6_ChangeUserData extends TestBase {

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
	public void changeUserData() {
		eppo = new EditProfilePageObject(driver);
		capo = new CreateAccountPageObject(driver);

		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		newNumber = capo.generateRandomPhoneNumber();

		eppo.changePhone(newNumber, "44");
		acfb = new AuthCodeFromBase();
		eppo.confirmCode(acfb.getAuthCode(newNumber));
		eppo.changeNames("newName", "newLName");
		driver.close();
	}

	@Test(priority = 4)
	public void validateChanges() {
		mppo.goToUserDetails();
		Assert.assertEquals(mppo.nameDisplay.getText(), "newName newLName", "Name not changed");
		Assert.assertEquals(mppo.phoneNumberDisplay.getText(), "+" + "44" + newNumber, "phone number not changed");

	}

	@AfterClass
	public void quitBrowser() {
		driver.quit();
	}
}
