package com.test.automation.Tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.test.automation.TestBase.TestBase;

public class GmailOperations extends TestBase {

	WebDriver driver;

	@FindBy(css = "a.gmail-nav__nav-link.gmail-nav__nav-link__sign-in")
	WebElement gmailSignIn;

	@FindBy(css = "input#identifierId")
	WebElement mailInput;

	@FindBy(css = "#identifierNext")
	WebElement nextButton;

	@FindBy(css = "input[type=password]")
	WebElement passwordInput;

	@FindBy(css = "#passwordNext")
	WebElement passwordNext;

	@FindBy(xpath = "(//td//div[@role='link'])[1]")
	WebElement firstCircleKMail;

	@FindBy(xpath = "(//td[@class='Bu']//a)[1]")
	WebElement linkToPasswordReset;

	@FindBy(xpath = "(//td[@class='Bu']//a)[1]//ancestor::div[1]")
	WebElement mailContent;

	public String mailText;

	public GmailOperations(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void logInToGmail(String mail, String password) {
		loadProperties();

		waitAndClick(gmailSignIn);
		waitAndType(mailInput, mail);
		nextButton.click();
		waitAndType(passwordInput, password);
		passwordNext.click();

	}

	public void getResetLink() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='UI']")));
		firstCircleKMail.click();
		getMailText();
		waitAndClick(linkToPasswordReset);
		driver.close();
	}

	public void getMailText() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		mailText = wait.until(ExpectedConditions.visibilityOf(mailContent)).getText();

	}

	public void waitAndClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();

	}

	public void waitAndType(WebElement element, String string) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(string);

	}

	public void verifyTranslationForgotPassword(String mail) {
		
		if(mail.equals("testcaseforgotkid@gmail.com")) {
			Assert.assertTrue(mailText.contains("Du har bedt om nullstilling av passordet."));
			Assert.assertTrue(mailText.contains("Bruk sikkerhetskoden under"));
			Assert.assertTrue(mailText.contains("eller klikk på linken for å komme til endring av passord:"));
		}
		
		else if(mail.equals("testcaseforgotkids@gmail.com")) {
			Assert.assertTrue(mailText.contains("Du har begärt nytt lösenord."));
			Assert.assertTrue(mailText.contains("Vänligen använd tecknet nedan"));
			Assert.assertTrue(mailText.contains("Eller klicka på länken för byte av lösenord:"));
		}
		
		else if(mail.equals("testcaseforgotkidd@gmail.com")) {
			Assert.assertTrue(mailText.contains("Kære bruger,"));
			Assert.assertTrue(mailText.contains("Du har bedt om en nulstilling af adgangskode"));
			Assert.assertTrue(mailText.contains("Brug venligst token nedenfor"));
			Assert.assertTrue(mailText.contains("Eller klik på et link for at omdirigere dig til ændring af kodeord:"));
		}
	}

	public void verifyTranslationChangeMail(String mail) {

		if (mail.toLowerCase().contains("s")) {
			Assert.assertTrue(mailText.contains("Vänligen bekräfta din e-postadress genom att klicka på länken"));
			
		}

		if (mail.toLowerCase().contains("n")) {
			Assert.assertTrue(mailText.contains("Vær vennlig og bekreft din E-post ved å klikke på linken"));
			
		}
		if (mail.toLowerCase().contains("d")) {
			Assert.assertTrue(mailText.contains("Bekræft venligst din email ved at klikke på linket"));
			
		}
	}
}
