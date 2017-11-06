package com.test.automation.PageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automation.TestBase.TestBase;

	
	
public class ExtraDashboardPageObject extends TestBase {
	
	 WebDriver driver;
	
	 @FindBy(css="#user-info a p")
	 WebElement profileButton;
	 
	 @FindBy(css="section [id='awards'] div[class='ng-scope']")
	 WebElement anyAward;           
	 
	 @FindBy(css="section [id='rolling-models'] div")	 
	 public WebElement anyRollingModel;         
	 
	 @FindBy(css="section [id='fuel-graph']")	 
	 WebElement fuelGraph;          
	 
	 @FindBy(id="transactions")	 
	 WebElement myTransactionsButton; 
	 
	 @FindBy(css="#points button")	
	 WebElement pointsDetailsButton;    
	 
	 @FindBy(css="#user-info a p") 
	 WebElement userProfile;     
	 
	 @FindBy(css="footer ul li") 
	 public  WebElement goToCustomerService;          
		 
	 @FindBy(id="points-summary")
	 WebElement pointsSummary;              
	 
	@FindBy(css="#banners img, #vouchers img")
	public List<WebElement> vouchers;
	 
	 public ExtraDashboardPageObject(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	 
	 public void goToProfile() {
		 
		 WebDriverWait wait = new WebDriverWait(driver,10);
		 wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	 public void goToAnyAward() {
		 WebDriverWait wait = new WebDriverWait(driver,10);
		 wait.until(ExpectedConditions.elementToBeClickable(anyAward)).click();
	 }
	 
	 public void goToAnyRollingModel() {
		 WebDriverWait wait = new WebDriverWait(driver,10);
		/* ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", anyRollingModel);*/
		 ((JavascriptExecutor) driver).executeScript("scroll(0, 250);");
		 wait.until(ExpectedConditions.elementToBeClickable(anyRollingModel)).click();
	 }
	 
	 public void clickOnFuelGraph() {
		 WebDriverWait wait = new WebDriverWait(driver,10);
		 /*((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fuelGraph);*/
		 ((JavascriptExecutor) driver).executeScript("scroll(0, 350);");
		 
		
				 wait.until(ExpectedConditions.elementToBeClickable(fuelGraph)).click();
	 }
	 
	 public void clickOnMyTransactionsButton() {
		 WebDriverWait wait = new WebDriverWait(driver,10);
		/* ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", myTransactionsButton);*/
		 ((JavascriptExecutor) driver).executeScript("scroll(0, 550);");
		 wait.until(ExpectedConditions.elementToBeClickable(myTransactionsButton)).click();
	 }
	 
	 public void clickOnPointsDetailsButton() {
		 WebDriverWait wait = new WebDriverWait(driver,10);
		 wait.until(ExpectedConditions.elementToBeClickable(pointsDetailsButton)).click();
	 }
	 
	 public void clickOnGoToCustomerService() {
		 WebDriverWait wait = new WebDriverWait(driver,10);
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", goToCustomerService);
		 wait.until(ExpectedConditions.elementToBeClickable(goToCustomerService)).click();
	 }
	 
	 public void clickOnPointsSummary() {
		 WebDriverWait wait = new WebDriverWait(driver,10);
		 wait.until(ExpectedConditions.elementToBeClickable(pointsSummary)).click();
	 }
	 
	 public void clickOnGoToService() {
		 WebDriverWait wait = new WebDriverWait(driver,10);
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", goToCustomerService);
		 wait.until(ExpectedConditions.elementToBeClickable(goToCustomerService)).click();
	 }
}
