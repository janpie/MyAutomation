package com.test.automation.UpdateAccount;

import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.PageObjects.LoginPageObject;
import com.test.automation.TestBase.TestBase;
import com.test.automation.Tools.GmailOperations;

public class TC22_ForgotPassword extends TestBase {
	
	GmailOperations gmail;
	LoginPageObject lpo;
	String randomPasswordString;

	@DataProvider(name="forgotPasswordAccounts")
	public String[][] data(){
		return getData("ForgotPassword", "Arkusz1");
	}
	
	@BeforeMethod
	public void runBrowser() {
		
		loadProperties();
		init(prop.getProperty("browser"), prop.getProperty("baseURL"));
		gmail = new GmailOperations(driver);
		lpo = new LoginPageObject(driver);
	
	}
	
	@Test(dataProvider="forgotPasswordAccounts")
	public void resetForgotPassword(String mail, String password) {
		lpo.forgotPasswordWithMail(mail);
		driver.get(prop.getProperty("Gmail"));
		gmail.logInToGmail(mail, password);
		gmail.getResetLink();
		gmail.verifyTranslationForgotPassword(mail);
		ArrayList <String> windows = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(windows.get(0));
		Random r = new Random();
		int randomPasswordInt = r.nextInt(111111)+11111;
		randomPasswordString= "A"+String.valueOf(randomPasswordInt)+"//";
		lpo.setPasswordTo(randomPasswordString);
		driver.close();
		driver = new ChromeDriver();
		lpo = new LoginPageObject(driver);
		driver.get(prop.getProperty("baseURL"));
		lpo.logInWithCredentials(mail, randomPasswordString);
		lpo.verifySuccessfulLogin();
		
	}
	
	
	
	
	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}

}
