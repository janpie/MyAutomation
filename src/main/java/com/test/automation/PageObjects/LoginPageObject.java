package com.test.automation.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.test.automation.TestBase.TestBase;



public class LoginPageObject extends TestBase{
	
	WebDriver driver;
	ExtraDashboardPageObject edpo;
	
	@FindBy(css="legend[class='ng-scope']")
	public WebElement loginHeader;
	
	@FindBy(css="#login-email-input-field")
	WebElement emailInput;
	
	@FindBy(id="login-password-input-field")
	WebElement passwordInput;
	
	@FindBy(id="login-submit-button")
	WebElement logInButton;
	
	@FindBy(css="h2#page-title")
	WebElement titleAssert;
	
	@FindBy(css=".message.ng-binding")
	WebElement invalidLoginMessage;
	
	@FindBy(css=".forgot-password")
	WebElement forgotPasswordLink;
	
	@FindBy(css="#forgotPasswordEmail-field")
	WebElement forgotPasswordMailInput;
	
	@FindBy(css="button[promise-state='resetRequest']")
	WebElement sendForgotPasswordMail;
	
	@FindBy(css="#set-new-password-input-field")
	WebElement newPasswordInput;
	
	@FindBy(css="button[promise-state='resetPromise']")
	WebElement resetPasswordSubmitButton;
	
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void logInWithCredentials(String mail, String password) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(emailInput));
		emailInput.sendKeys(mail);
		passwordInput.sendKeys(password);
		logInButton.click();
	}
	
	public void verifySuccessfulLogin() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		edpo = new ExtraDashboardPageObject(driver);
		wait.until(ExpectedConditions.visibilityOf(edpo.anyRollingModel));
		Assert.assertEquals(driver.getTitle(), "Circle K - Extra 2.0 - EXTRA Club");
		
	}
	
	public void verifyInvalidLogin() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(invalidLoginMessage));
		Assert.assertEquals(driver.getTitle(), "Circle K");
		
	}

	public void forgotPasswordWithMail(String mail) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
		wait.until(ExpectedConditions.visibilityOf(forgotPasswordMailInput)).sendKeys(mail);
		sendForgotPasswordMail.click();
	}
	
	public void setPasswordTo(String newPassword) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(newPasswordInput)).sendKeys(newPassword);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resetPasswordSubmitButton);
		wait.until(ExpectedConditions.elementToBeClickable(resetPasswordSubmitButton)).click();
		
	}
}
