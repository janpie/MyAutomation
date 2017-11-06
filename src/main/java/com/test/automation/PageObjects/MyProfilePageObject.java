package com.test.automation.PageObjects;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.test.automation.TestBase.TestBase;

public class MyProfilePageObject extends TestBase {

	WebDriver driver;

	@FindBy(css = "h3.ng-binding")
	public WebElement nameDisplay;

	@FindBy(css = "#personal-info p:nth-of-type(1)")
	public WebElement mailDisplay;

	@FindBy(css = "#personal-info p:nth-of-type(2)")
	public WebElement phoneNumberDisplay;

	@FindBy(css = "button.inline.ng-scope")
	WebElement updateAccount;

	@FindBy(css = ".card-item.ng-scope div")
	public List<WebElement> addedCardsList;

	@FindBy(css = ".list-button-add-card.ng-scope")
	WebElement addCardsButton;

	@FindBy(css = "button[ng-click*='showMyTransactions']")
	WebElement showTransactionsButton;

	@FindBy(css = "button[ng-click*='showPoints']")
	WebElement showPointsButton;

	@FindBy(css = "#agreement-item")
	WebElement addAgreementCodeButton;

	@FindBy(css = "#terms-conditions")
	public WebElement goToTermsAndConditions;

	@FindBy(css = "#communication-preferences")
	WebElement goToCommunicationPreferences;

	///////////// T&C submenu
	@FindBy(css = "div[ng-bind-html='terms_and_conditions_body']")
	public WebElement tcContent;

	@FindBy(css = "button[ng-click*='showProfile']")
	WebElement showBackProfileTC;

	//////////////// Card submenu
	@FindBy(css = "button[ng-click*='removeCard']")
	WebElement removeCard;

	@FindBy(css = "button[ng-click*='showProfile']")
	WebElement showBackProfileCard;

	///////////// Agreement code submenu
	@FindBy(tagName = "input")
	WebElement codeInput;

	@FindBy(css = "button[ng-click*='updateAgreementCode']")
	WebElement updateCodeButton;

	@FindBy(css = "button[ng-click*='showProfile']")
	WebElement showBackProfileAgreement;

	////////////// Communication Preferences

	@FindBy(css = "input#email")
	public WebElement emailTickbox;

	@FindBy(css = "input#sms")
	public WebElement smsTickbox;

	@FindBy(css = "#setCommunicationPreferences")
	WebElement saveCommunication;

	@FindBy(css = "#showProfile")
	WebElement backToProfile;

	@FindBy(css = "div#sms-container label")
	public WebElement smsLabel;
	
	@FindBy(css = "div#email-container label")
	public WebElement emailLabel;

	public MyProfilePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goToAccountUpdate() {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(updateAccount)).click();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
	}

	public void goToUserDetails() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs2.get(0));

		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(nameDisplay));
		wait.until(ExpectedConditions.textToBePresentInElement(nameDisplay, "new"));

	}

	public void goToAddCard() {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(addCardsButton)).click();

	}

	public void goToTC() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", goToTermsAndConditions);
		wait.until(ExpectedConditions.elementToBeClickable(goToTermsAndConditions)).click();
	}

	public void goToAllTransactionsThroughProfile() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(showTransactionsButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", showTransactionsButton);
		wait.until(ExpectedConditions.elementToBeClickable(showTransactionsButton)).click();
	}

	public void goToPointsThroughProfile() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(showPointsButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", showPointsButton);
		wait.until(ExpectedConditions.elementToBeClickable(showPointsButton)).click();
	}

	public void goToCommunicationPreferences() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(goToCommunicationPreferences));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", goToCommunicationPreferences);
		wait.until(ExpectedConditions.elementToBeClickable(goToCommunicationPreferences)).click();
	}

	public void changeAndCheckCommunicationPreferences(WebElement tickbox, WebElement label) {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		if (tickbox.isSelected()) {
			label.click();
			saveCommunication.click();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", backToProfile);
			wait.until(ExpectedConditions.elementToBeClickable(backToProfile)).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			goToCommunicationPreferences();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			Assert.assertFalse(tickbox.isSelected(), "Tickbox not disabled");
		} else {
			label.click();
			saveCommunication.click();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", backToProfile);
			wait.until(ExpectedConditions.elementToBeClickable(backToProfile)).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			goToCommunicationPreferences();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			Assert.assertTrue(tickbox.isSelected(), "Tickbox not enabled");
		}

	}

	public void atLeastOneEnabled(WebElement tickbox, WebElement label) {

		if (tickbox.isSelected() == false) {
			label.click();
		}
	}

}
