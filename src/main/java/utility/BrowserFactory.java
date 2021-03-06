package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;



public class BrowserFactory {

	static WebDriver driver;
	
	
	public static WebDriver getBrowser(String browserName)
	{
		
		if(browserName.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			
		}
		else if(browserName.equalsIgnoreCase("Chrome"))
		{
			
			System.setProperty("webdriver.chrome.driver",ReadProperty.loadProperty("chromedriver_path"));
			
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver",ReadProperty.loadProperty("IEDriverServer_path"));
			
			driver=new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(ReadProperty.loadProperty("url"));
		return driver;
		
	}
	
	public static void closeBrowser(WebDriver ldriver)
	{
		ldriver.quit();
	}
	
	
}
