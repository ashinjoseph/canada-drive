package common;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testcases.TC_BuyVehicle;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class WebFunctions extends TC_BuyVehicle {
	public WebDriver driver;

	public WebFunctions(WebDriver driver)

	// Initializing variables
	{
		this.driver = driver;
	}

	public static void scrollDownUntillElementVisible(WebDriver driver,
			WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	@SuppressWarnings("static-access")
	public ExtentTest getExtentTestInstance() {
		return this.test;
	}

	@SuppressWarnings("static-access")
	public void setExtentTestInstance(ExtentTest test) {
		this.test = test;
	}

	public static String getScreenshotInBase64(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;

		return ts.getScreenshotAs(OutputType.BASE64);
	}
	
	public static void capturescreenshot(WebDriver driver, String screenshotname) {

		try {

			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./Screenshots/" + screenshotname + ".png"));
			System.out.println("Screenshot taken for " + screenshotname);

		} catch (Exception e) {
			System.out.println("exception while taking screenshot" + e.getMessage());
		}
	}

	public static WebElement waitForWebElement(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static WebElement waitForWebElement(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public static void clickElement(WebDriver driver, By locator, String logInfo) {
		try {
			waitForWebElement(driver, locator);
			driver.findElement(locator).click();
			writeExtent("Pass", logInfo);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(logInfo);
			writeExtent("Fail", logInfo);
			org.testng.Assert.assertFalse(true, logInfo);
		}

	}

	public static void type(WebDriver driver, By locator, String textToType,String logInfo) {
		try {
			waitForWebElement(driver, locator);
			driver.findElement(locator).sendKeys(textToType);
			writeExtent("Pass", "Entered " + logInfo+" as "+textToType);
		} catch (Exception e) {
			System.out.println("Could not enter " + logInfo + " as " + textToType);
			writeExtent("Fail", "Could not enter " + logInfo + " as " + textToType);
			org.testng.Assert.assertFalse(true, "Could not enter " + logInfo + " as " + textToType);
		}
		
	}

	public static void writeExtent(String Status, String Details) {

		if (Status.equals("Pass"))
			test.log(LogStatus.PASS, Details);
		else if (Status.equals("Fail"))
			test.log(LogStatus.FAIL, Details);
		else if (Status.equals("Info"))
			test.log(LogStatus.INFO, Details);
	}

	public void waitForSync(int i) {
		try {

			int j = i * 1000;
			Thread.sleep(j);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void onPassUpdate(String logInfo) {
		try {
			
			test.log(LogStatus.PASS, "Successfully Verified "+logInfo);
			String screenshotSuccess = "Successfully Verified @Test Method "
					+ System.currentTimeMillis();
			capturescreenshot(driver, screenshotSuccess);
			test.addScreenCapture("./Screenshots/" + screenshotSuccess + ".png");
			test.addScreencast("./Screenshots/" + screenshotSuccess + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onFailUpdate(String logInfo) {
		
		test.log(LogStatus.FAIL, "Failed to Verify " + logInfo);
		org.testng.Assert.assertFalse(true, "Element is not found");

	}

}
