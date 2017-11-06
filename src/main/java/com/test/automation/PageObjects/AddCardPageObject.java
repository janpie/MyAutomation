package com.test.automation.PageObjects;

import java.util.List;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.test.automation.TestBase.TestBase;
import com.test.automation.Tools.CardGenerator;


public class AddCardPageObject extends TestBase {

WebDriver driver;

	@FindBy(css="label[for='RegularCard']")
	WebElement regularCardCheckbox;
	
	@FindBy(css="button[class='button-solid ng-scope']")
	WebElement continueButton;
	
	@FindBy(css="#cardNumber")
	WebElement cardNumberInput;
	
	@FindBy(css="button[class='submit']")
	WebElement submitCardNumberButton;
		
	
	public AddCardPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void addRegularCard() {
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 CardGenerator cg = new CardGenerator();
		 
		  wait.until(ExpectedConditions.elementToBeClickable(regularCardCheckbox)).click();
		  continueButton.click();
		  for (int i = 0; i < 10; i++){
			  wait.until(ExpectedConditions.visibilityOf(cardNumberInput)).sendKeys(cg.generate("5", 16));
			  submitCardNumberButton.click();  
			  List <WebElement> error = driver.findElements(By.cssSelector("p[class='error']"));
			   
			  if (error.isEmpty()){
				  break;
			  }
			  else{
				  cardNumberInput.clear();
			  }
			  }
		  
	}
	
	public void addRegularCard(String prefix) {
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 CardGenerator cg = new CardGenerator();
		 
		 int h = 0;
		 
		  wait.until(ExpectedConditions.elementToBeClickable(regularCardCheckbox)).click();
		  continueButton.click();
		  for (int i = 0; i < 4; i++){
			  wait.until(ExpectedConditions.visibilityOf(cardNumberInput)).sendKeys(cg.generate(prefix, 16));
			  submitCardNumberButton.click();  
			  List <WebElement> error = driver.findElements(By.cssSelector("p[class='error']"));
			   
			  if (error.isEmpty()){
				  h=1;
				  break;
			  }
			  else{
				  cardNumberInput.clear();
			  }
			  }
		  if(h==0) {
			  driver.findElement(By.cssSelector("button[name=abort]")).click();
			  try {
				Assert.assertTrue(false, "Cannot add the card");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		  }
		  
		  
		  
	}
	
	public void addEstoniaCard(String prefix, String type) {
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 CardGenerator cg = new CardGenerator();
		 
		 int h = 0;
		 
		  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='"+type+"']"))).click();
		  continueButton.click();
		  for (int i = 0; i < 4; i++){
			  wait.until(ExpectedConditions.visibilityOf(cardNumberInput)).sendKeys(cg.generate(prefix, 16));
			  submitCardNumberButton.click();  
			  List <WebElement> error = driver.findElements(By.cssSelector("p[class='error']"));
			   
			  if (error.isEmpty()){
				  h=1;
				  break;
			  }
			  else{
				  cardNumberInput.clear();
			  }
			  }
		  if(h==0) {
			  driver.findElement(By.cssSelector("button[name=abort]")).click();
			  try {
				Assert.assertTrue(false, "Cannot add the card");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		  }
		  
		  
		  
	}
}
