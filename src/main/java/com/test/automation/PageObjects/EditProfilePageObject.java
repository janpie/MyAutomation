package com.test.automation.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automation.TestBase.TestBase;



public class EditProfilePageObject extends TestBase{
	
	WebDriver driver;
	/////////name edit////////
	@FindBy(css="button[ng-click='editName()']")
	WebElement editNameButton;
	
	@FindBy(css="#input-edit-first-name")
	WebElement inputName;
	
	@FindBy(css="#input-edit-last-name")
	WebElement inputLName;
	
	//////////////mail edit////////
	
	@FindBy(css="button[ng-click='editEmail()']")
	WebElement editMailButton;
	
	@FindBy(css="#input-edit-email")
	WebElement inputMail;
	
	
	///////////phone edit////////
	
	@FindBy(css="button[ng-click='editPhone()']")
	WebElement editPhoneButton;

	@FindBy(css="#edit-country-code")
	WebElement inputCountryCode;
	
	@FindBy(css="#edit-phone-number")
	WebElement inputPhoneNumber;
	
	@FindBy(css="button[type=submit]")
	WebElement nextButton;
	
	@FindBy(css="input#confirmation-code-input")
	WebElement confirmationCodeInput;
	
	@FindBy(css=".button.primary.visible-succeed")
	WebElement finishButton;
	
	/////////////password edit////////
	
	@FindBy(css="button[ng-click='changePassword()']")
	WebElement editPasswordButton;
	
	@FindBy(css="#password-input-new-field")
	WebElement newPasswordInput;
	
	////////////////zip code edit////////
	
	@FindBy(css="button[ng-click='editZipCode()']")
	WebElement editZipCodeButton;
	
	@FindBy(css="#input-edit-zipcode")
	WebElement inputZipCode;
	
	///////////gender edit////////
	
	@FindBy(css="button[ng-click='editGender()']")
	WebElement editGenderButton;
	
	@FindBy(css="select#gender-selector")
	WebElement selectGenderDropdown;
	
	///////////////birthday edit///////
	
	@FindBy(css="button[ng-click='editBirthday()']")
	WebElement editBirthdayButton;
	
	////////generic fields/////
	@FindBy(css="#password-input-field")
	WebElement inputPassword;
	
	@FindBy(css=".button.primary.visible-succeed.ng-scope")
	WebElement saveChanges;
	
	@FindBy(css="a.button.ng-scope")
	WebElement finishSaveButton;
	
	@FindBy(css=".clear.button.ng-scope")
	WebElement closeEditWindow;
	
	@FindBy(css=".message.ng-binding")
	public WebElement errorMessage;
	
	@FindBy(xpath="//a[@ng-click='deleteAccount()']")
	WebElement deleteAccountLink;
	
	@FindBy(css="#input-validationPhrase")
	WebElement validationPhraseInput;
	
	@FindBy(css="button[type='submit']")
	WebElement submitDelete;
	
	public EditProfilePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void changeNames(String newName, String newLName){
		WebDriverWait wait = new WebDriverWait(driver,10);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(editNameButton)).click();
		wait.until(ExpectedConditions.visibilityOf(inputName)).clear();
		inputName.sendKeys(newName);
		inputLName.clear();
		inputLName.sendKeys(newLName);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveChanges);
		saveChanges.click();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void changeMail(String newMail) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(editMailButton)).click();
		wait.until(ExpectedConditions.visibilityOf(inputMail)).sendKeys(newMail);
		inputPassword.sendKeys("Assa123//");
		saveChanges.click();
	}
	
	public void changePhone(String newPhone, String countryCode){
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(editPhoneButton)).click();
		wait.until(ExpectedConditions.visibilityOf(inputCountryCode)).clear();
		inputCountryCode.sendKeys(countryCode);
		inputPhoneNumber.clear();
		inputPhoneNumber.sendKeys(newPhone);
		inputPassword.sendKeys("Assa123//");
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
		wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
	
	
	}
	
	
	
	public void confirmCode(String validationCode){
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		//Actions act = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOf(confirmationCodeInput)).sendKeys(validationCode);
		wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
		//act.moveToElement(validationCodeInput)).sendKeys(validationCode).build().perform();
		
		
		
		
	}
	
	public void closeAccountWithPhrase(String phrase) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(deleteAccountLink));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteAccountLink);
		deleteAccountLink.click();
		wait.until(ExpectedConditions.visibilityOf(validationPhraseInput)).sendKeys(phrase);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitDelete);
		submitDelete.click();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
}
