package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import com.relevantcodes.extentreports.LogStatus;
import common.BaseSetup;
import common.ExcelRead;
import common.WebFunctions;
import functions.BuyVehicleFromInventory;

public class TC_BuyVehicle extends BaseSetup {
	int counter = 0;
	public ExcelRead excelRead;
	public BuyVehicleFromInventory buyVehicleFromInventory;
	public WebFunctions libr;
	String path1 = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\ApplicationData.xls";
	String sheetName = "TestData";

	@BeforeClass
	public void setup() {
		libr = new WebFunctions(driver);
		excelRead = new ExcelRead();
		buyVehicleFromInventory = new BuyVehicleFromInventory(driver);

	}

	@DataProvider(name = "BuyVehicle")
	public Object[][] createData() throws Exception {
		Object[][] retObjArr1 = excelRead.getMapArray(path1, sheetName,
				"BuyVehicle");
		return retObjArr1;

	}

	@SuppressWarnings("static-access")
	@Test(dataProvider = "BuyVehicle")
	public void getTestSuite(Map<Object, Object> map) {

		try {

			String strUrl = driver.getCurrentUrl();
			test.log(LogStatus.INFO, "Browser Lunched & Loaded url: " + strUrl);
			buyVehicleFromInventory.buyVehicle(map);
			buyVehicleFromInventory.verifyBuyVehicle(map);

		} catch (Exception e) {

			String screenshotSuccess = "Failure in @Test Method "
					+ System.currentTimeMillis();
			libr.capturescreenshot(driver, screenshotSuccess);
			test.addScreenCapture("./Screenshots/" + screenshotSuccess + ".png");
			test.log(LogStatus.FAIL, "Element is not found");
			e.printStackTrace();
			Assert.assertFalse(true, "Element is not found");

		}
	}
}
