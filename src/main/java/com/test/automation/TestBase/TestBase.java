package com.test.automation.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.test.automation.Tools.ExcelReader;

public class TestBase {

	public WebDriver driver;
	public ExcelReader er;
	public static Properties prop;
	DesiredCapabilities caps = new DesiredCapabilities();
	final String USERNAME = "marker2";
	final String AUTOMATE_KEY = "xxx";
	final String URL = "xxx";

	public void init(String browser, String url) {
		startBrowser(browser);
		getUrl(url);

	}

	public void startBrowser(String browser) {

		if (browser.equalsIgnoreCase("firefox")) {

			caps.setCapability("acceptInsecureCerts", true);
			FirefoxOptions options = new FirefoxOptions().addPreference("intl.accept_languages", "en")
					.addCapabilities(caps);

			driver = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("opera")) {
			driver = new OperaDriver();
		} else if (browser.equalsIgnoreCase("remoteIE")) {

			caps.setCapability("acceptSslCerts", "true");
			caps.setCapability("browser", "IE");
			caps.setCapability("browser_version", "11.0");
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("resolution", "2048x1536");
			try {
				URL serverUrl = new URL(URL);
				driver = new RemoteWebDriver(serverUrl, caps);
				driver.manage().window().maximize();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		else if (browser.equalsIgnoreCase("remoteOpera")) {

			caps.setCapability("acceptSslCerts", "true");
			caps.setCapability("browser", "Opera");
			caps.setCapability("browser_version", "12.16");
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "8.1");
			caps.setCapability("resolution", "2048x1536");
			try {
				URL serverUrl = new URL(URL);
				driver = new RemoteWebDriver(serverUrl, caps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		else if (browser.equalsIgnoreCase("remoteSafari")) {

			caps.setCapability("acceptSslCerts", "true");
			caps.setCapability("browser", "Safari");
			caps.setCapability("browser_version", "10.1");
			caps.setCapability("os", "OS X");
			caps.setCapability("os_version", "Sierra");
			caps.setCapability("resolution", "1920x1080");
			try {
				URL serverUrl = new URL(URL);
				driver = new RemoteWebDriver(serverUrl, caps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		else if (browser.equalsIgnoreCase("remoteEdge")) {

			caps.setCapability("acceptSslCerts", "true");
			caps.setCapability("browser", "Edge");
			caps.setCapability("browser_version", "15.0");
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("resolution", "2048x1536");
			try {
				URL serverUrl = new URL(URL);
				driver = new RemoteWebDriver(serverUrl, caps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		else if (browser.equalsIgnoreCase("headless")) {
			
			ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1200x600");
            driver = new ChromeDriver(options);
		}

	}

	public void getUrl(String url) {

		// driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		
	}

	public String[][] getData(String excelName, String sheetName) {
		er = new ExcelReader(System.getProperty("user.dir")
				+ "//src//main//java//com//test//automation//DataProviders//" + excelName + ".xlsx");
		String data[][] = er.getExcelData(sheetName);
		return data;

	}

	public void getScreenshot(String name) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "\\src\\main\\java\\com\\test\\automation\\Screenshots\\";
			File destFile = new File(
					(String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void loadProperties() {
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\test\\automation\\DataProviders\\config.properties");
			prop.load(file);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
