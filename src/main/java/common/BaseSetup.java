package common;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utility.BrowserFactory;
import utility.ReadProperty;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseSetup {
	public static java.util.Map<Object, Object> baseMap;
	public WebDriver driver;
	protected ExtentReports extent;
	protected static ExtentTest test;
	public WebFunctions libr;

	Calendar calendar = Calendar.getInstance();
	Date date = calendar.getTime();
	DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__HH_mm_ss");
	final String filePath = ".\\reports\\html\\ExtentReport_"
			+ dateFormat.format(date) + ".html";

	@SuppressWarnings("static-access")
	@AfterMethod
	protected void afterMethod(ITestResult result) {
		libr = new WebFunctions(driver);
		driver.manage().deleteAllCookies();
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotSuccess = "Failure in @Test Method "
					+ System.currentTimeMillis();
			libr.capturescreenshot(driver, screenshotSuccess);
			test.addScreenCapture("./Screenshots/" + screenshotSuccess + ".png");
			test.addScreencast("./Screenshots/" + screenshotSuccess + ".png");
			test.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			test.log(LogStatus.PASS, "Test passed");
		}

		ExtendManager.getReporterInstance().endTest(test);
		ExtendManager.getReporterInstance().flush();
	}

	@BeforeClass
	public void setUpClass() {

		driver.manage().window().maximize();

	}

	@BeforeMethod
	public void startExtent(Method method) {

		test = ExtendManager.getReporterInstance().startTest(
				method.getDeclaringClass().getSimpleName());
	}

	@BeforeSuite
	public void beforeSuite() {
		driver = BrowserFactory
				.getBrowser(ReadProperty.loadProperty("browser"));
		extent = ExtendManager.getReporter(filePath);
	}

	@AfterSuite
	protected void afterSuite() {
		extent.close();
	}

	@AfterClass
	public void tearDownClass() {

		if (driver != null) {

			try {
				driver.quit();

			} catch (Exception e) {
				e.getMessage();
			}

		}
	}

}
