package com.test.automation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.test.automation.TestBase.TestBase;



public class PointsMenuPageObject extends TestBase{
	
	WebDriver driver;
	
	@FindBy(id="top-image")
	WebElement topImage; 
	
	@FindBy(css=".back a")
	WebElement backMonth; 
	
	@FindBy(css=".next a")
	WebElement nextMonth; 
	
	@FindBy(css=".month.ng-binding")
	public WebElement selectedMonth; 
	
	@FindBy(id="points-earned")
	public WebElement pointsEarned; 
	
	@FindBy(id="points-spent")
	public WebElement pointsSpent; 
	
	@FindBy(css="#points-expire p[class='ng-binding ng-scope']")
	public WebElement pointsExpire; 
	
	@FindBy(tagName="ck-back-button")
	public WebElement backButton; 
	
	
	
	public PointsMenuPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void nextMonth(){
		WebDriverWait wait = new WebDriverWait(driver,10);
		 wait.until(ExpectedConditions.elementToBeClickable(nextMonth)).click();
		
	}
	
	public void previousMonth(){
		WebDriverWait wait = new WebDriverWait(driver,10);
		 wait.until(ExpectedConditions.elementToBeClickable(backMonth)).click();
	}
	

}
