package com.test.automation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automation.TestBase.TestBase;


public class MyTransactionsPageObject extends TestBase {

WebDriver driver;

@FindBy(css="div a[ng-click='goBack()']")
WebElement backMonth; 

@FindBy(css="div a[ng-click='goNext()']")
public WebElement nextMonth; 

@FindBy(css=".heading.ng-binding")
public WebElement selectedMonth; 

@FindBy(css=".value-graph-container")
public WebElement fuelGraph; 

@FindBy(css="div[class='fuel-summary'] h2 span")
public WebElement fuelAmount;

@FindBy(xpath="//div[@class='month-summary']//h2//span")
public WebElement moneyAmount;

@FindBy(css=".month-summary")
public WebElement transactionsMonthSummary; 

@FindBy(css=".all-transactions.ng-binding.current")
public WebElement allTransactionsHeader;

@FindBy(css="#transactions-container")
public WebElement allTransactionsTable; 

@FindBy(css=".fuel-consumption.ng-binding")
public WebElement fuelConsumptionHeader; 

@FindBy(tagName="ck-back-button")
WebElement backButton; 
	
	
		
	
	public MyTransactionsPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean isHeaderSelected(WebElement element){
		
		String classValue = element.getAttribute("class");
		if(classValue.contains("current")){
			return true;
		}
		else{
			return false;
		}
	
		  
	
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
