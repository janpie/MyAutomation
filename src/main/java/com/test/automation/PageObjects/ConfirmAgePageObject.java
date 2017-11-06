package com.test.automation.PageObjects;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automation.TestBase.TestBase;



public class ConfirmAgePageObject extends TestBase{
	
	WebDriver driver;
	
	@FindBy(css="select[ng-model='year']")
	WebElement selectYear;
	
	@FindBy(css="select[ng-model='month']")
	WebElement selectMonth;
	
	@FindBy(css="select[ng-model='day']")
	WebElement selectDay;
	
	@FindBy(css="button[class='button-solid ng-scope']")
	public
	WebElement confirmButton;
	
	@FindBy(css="p[class='ng-binding']")
	public
	WebElement ageNotAllowedPopUp;
	
	
	
	public ConfirmAgePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void sendInvalidAge(){
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver,30);
		Select drpYear = new Select(selectYear);
		Select drpMonth = new Select(selectMonth);
		Select drpDay = new Select(selectDay);
		
		Random r = new Random();
		int randomInvalidYear = 2002+ r.nextInt(15);
		int randomDay = r.nextInt(29) +1;
		
		wait.until(ExpectedConditions.elementToBeClickable(selectYear));
		drpYear.selectByValue(Integer.toString(randomInvalidYear));
		wait.until(ExpectedConditions.elementToBeClickable(selectMonth));
		drpMonth.selectByIndex(3);
		if(randomDay>=10){
		wait.until(ExpectedConditions.elementToBeClickable(selectDay));
		drpDay.selectByValue(Integer.toString(randomDay));
		}
		else{
			wait.until(ExpectedConditions.elementToBeClickable(selectDay));
			drpDay.selectByValue("0"+Integer.toString(randomDay));
		}
		
	
	}
	
	public void sendValidAge(){
		Select drpYear = new Select(selectYear);
		Select drpMonth = new Select(selectMonth);
		Select drpDay = new Select(selectDay);
		
		Random r = new Random();
		int randomValidYear = 1900+ r.nextInt(99);
		int randomDay = r.nextInt(29) +1;
		
		drpYear.selectByValue(Integer.toString(randomValidYear));
		drpMonth.selectByIndex(3);
		if(randomDay>=10){
			drpDay.selectByValue(Integer.toString(randomDay));
			}
			else{
				drpDay.selectByValue("0"+Integer.toString(randomDay));
			}
		confirmButton.click();
	}
}

