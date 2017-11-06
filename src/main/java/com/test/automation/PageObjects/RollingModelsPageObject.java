package com.test.automation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.test.automation.TestBase.TestBase;


public class RollingModelsPageObject extends TestBase {

WebDriver driver;

	@FindBy(css=".ng-scope.ng-binding:nth-of-type(2)")
	public WebElement description;
	
	@FindBy(css=".rolling-model-item")
	public WebElement awardPicture;
	
	
		
	
	public RollingModelsPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
		  
	
}
