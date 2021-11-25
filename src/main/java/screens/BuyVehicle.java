package screens;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import common.WebFunctions;

public class BuyVehicle extends WebFunctions {

	public BuyVehicle(WebDriver driver) {
		super(driver);
	}

	// make and model span
	By make_span = By.xpath("//span[text()='Make & Model']");
	// Get Started Span
	By getStarted_span = By
			.xpath("//button/span[contains(text(),' Get Started')]");
	// confirm message
	By messageConfirm_button = By
			.xpath("//div[starts-with(@class,'dialog')]/*//span[contains(text(),'Confirm')]");
	// Payment calculator
	By calculateDelivery_button = By
			.xpath("//div[contains(text(),'Calculate Delivery')]");
	By selectWarranty_button = By
			.xpath("//div[contains(text(),'Select Warranty')]");
	By deliveryAddress_txtbox = By.xpath("//input[@id='street_address']");
	By save_button = By.xpath("//span[contains(text(),'Save and Confirm')]");
	By confirmProtectionPurchase_button= By.xpath("//div[contains(text(),'Protect Your Purchase')]/../../../../../div/*//span[contains(text(),'Save and Confirm')]");
	//verify Vehicle Details
	By vehicleDetails_span= By.xpath("(//div[starts-with(@class,'vehicle-info')]/div[2]/div/div[1])[2]");
	
	public void SelectMake(String make) throws InterruptedException {
		waitForSync(1);
		clickElement(driver, make_span, "Click on make & model span");
		waitForSync(2);
		String make_Xpath = "//span[text()='Make']".replace("Make", make);
		By makeEle = By.xpath(make_Xpath);
		scrollDownUntillElementVisible(driver, driver.findElement(makeEle));
		clickElement(driver, makeEle, "Select make " + make);

	}

	public void SelectModel(String model) {
		waitForSync(1);
		String model_Xpath = "//span[text()='Model']".replace("Model", model);
		By modelEle = By.xpath(model_Xpath);
		clickElement(driver, modelEle, "Select model " + model);

	}

	public void SelectVehicle(String make, String model, String selectionID) {
		waitForSync(4);
		String vechileSelection_Xpath = "(//div[@class='vehicle-card'])["
				+ selectionID + "]";
		By vechileSelectionEle = By.xpath(vechileSelection_Xpath);
		clickElement(driver, vechileSelectionEle, "Vehicle selection ID "
				+ selectionID);
	}

	public void ClickGetStarted() {
		waitForSync(3);
		clickElement(driver, getStarted_span, "Click on GetStarted");
		waitForSync(1);
		List<WebElement> popupMessage = driver
				.findElements(messageConfirm_button);
		if (popupMessage.size() > 0) {

			clickElement(driver, messageConfirm_button,
					"Click on confirm message button");
		}

	}

	public void SelectAddress(String address) {
		waitForSync(1);
		clickElement(driver, calculateDelivery_button,
				"Click on calculateDelivery_button");
		waitForSync(2);
		type(driver, deliveryAddress_txtbox, address,"deliveryAddress");
		waitForSync(1);
		driver.findElement(deliveryAddress_txtbox).sendKeys(Keys.BACK_SPACE);
		waitForSync(2);
		driver.findElement(deliveryAddress_txtbox).sendKeys(Keys.DOWN,Keys.ENTER, Keys.ENTER);
		waitForSync(5);
		clickElement(driver, save_button, "Click on deliveryAddress save button");
	}

	public void SelectWarranty(String warranty) {
		waitForSync(2);
		clickElement(driver, selectWarranty_button,
				"Click on selectWarranty_button");
		waitForSync(1);
		String warranty_Xpath = "//div[contains(text(),'Warranty')]".replace(
				"Warranty", warranty);
		By warrantyEle = By.xpath(warranty_Xpath);
		clickElement(driver, warrantyEle, "Select warranty as " + warranty);
		waitForSync(1);
		clickElement(driver, confirmProtectionPurchase_button, "Click on confirmProtectionPurchase_button");
	}

	public void verifyVehicleDetails(String vehicleDetailExpected) {
		String vehicleDetailActual=driver.findElement(vehicleDetails_span).getText().trim();
		if(vehicleDetailActual.contains(vehicleDetailExpected)){
			onPassUpdate("vehicleDetails as"+vehicleDetailExpected);
		}else {
			onFailUpdate("vehicleDetails as"+vehicleDetailExpected);
		}
}
	}
