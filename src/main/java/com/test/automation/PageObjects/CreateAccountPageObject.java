package com.test.automation.PageObjects;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automation.TestBase.TestBase;

public class CreateAccountPageObject extends TestBase {

WebDriver driver;

	@FindBy(xpath="//a[@class='button secondary ng-scope']")
	WebElement registerButton;
	
	@FindBy(id="edit-phone-number-country-code")
	WebElement countryCodeInput;
	
	@FindBy(id="edit-phone-number")
	WebElement phoneNumberInput;
	
	@FindBy(css="button[translate='validationCode.sendCode']")
	WebElement sendCodeButton;
	
	@FindBy(css="input#validation-code-input")
	WebElement validationCodeInput;
	
	@FindBy(xpath="(//div[@class='columns']//button)[1]")
	WebElement verifyCodeButton;
	
	@FindBy(xpath="//button[text()='Resend Code']")
	WebElement resendCodeButton;
	
	@FindBy(css="legend[class='ng-scope']")
	WebElement registerHeader;
	
	@FindBy(id="account-creation-email-input-field")
	WebElement emailInput;
	
	@FindBy(id="account-creation-password-input-field")
	WebElement passwordInput;
	
	@FindBy(css="#input-account-creation-first-name-input")
	WebElement fnameInput;
	
	@FindBy(css="#input-account-creation-last-name-input")
	WebElement lnameInput;
	
	@FindBy(id="edit-region-select")
	WebElement dropdownRegion;
	
	@FindBy(css="span.icon-container")
	WebElement termsCheckbox;
	
	@FindBy(css="button[class='button primary ng-scope'][promise-state='registerUserPromise']")
	WebElement letsGetStartedButton;
	
	@FindBy(css="h3[class=ng-scope]")
	WebElement authorizeAppHeader;
	
	@FindBy(id="authorize-app")
	WebElement allowButton;
	
	
	
	
	public CreateAccountPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void goToRegister() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
		
	}
	
	public String generateRandomPhoneNumber(){
		Random r = new Random();
		int phoneNumber = 11111111 + r.nextInt(99999);
		String phoneNumberString = Integer.toString(phoneNumber);
		return phoneNumberString;
		
	}
	
	public void sendRandomPhoneNumber(String number){
		Random r = new Random();
		int countryCode = 11 + r.nextInt(88);
		String countryCodeString = Integer.toString(countryCode);
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(countryCodeInput)).sendKeys(countryCodeString);
		phoneNumberInput.sendKeys(number);
		sendCodeButton.click();
		
	}
	
	public void sendAuthCode(String code) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(validationCodeInput)).sendKeys(code);
		verifyCodeButton.click();
	}
	
	
	
	public void registerCredentials(String mail, String password, String fname, String lname, int selectCountry){
		
		System.out.println(mail);
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(mail);
		passwordInput.sendKeys(password);
		fnameInput.sendKeys(fname);
		lnameInput.sendKeys(lname);
		Select dropdownRegion = new Select(driver.findElement(By.id("edit-region-select")));
		dropdownRegion.selectByIndex(selectCountry);
		((JavascriptExecutor) driver).executeScript("scroll(0,500);");
		termsCheckbox.click();
		wait.until(ExpectedConditions.elementToBeClickable(letsGetStartedButton)).click();
	}
	
	public void allowExtra() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(allowButton)).click();
	}
}
