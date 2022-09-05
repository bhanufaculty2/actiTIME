package generic;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	public WebDriver driver;
	public WebDriverWait wait;
	public static String configPath="./config.properties";
	public static String testDataPath="./data/TestData.xlsx";
	
	
	@BeforeMethod
	public void openApp() throws Exception
	{		
		String useGrid= Utility.getPropertyValue(configPath, "USEGRID");
		Reporter.log("Use Grid:"+useGrid,true);
		
		String gridURL= Utility.getPropertyValue(configPath, "GRIDURL");
		Reporter.log("Grid URL:"+gridURL,true);
		
		String appURL = Utility.getPropertyValue(configPath, "URL");
		Reporter.log("App URL:"+appURL,true);
		
		String strITO = Utility.getPropertyValue(configPath, "ITO");
		int intITO = Integer.parseInt(strITO);
		
		String strETO =  Utility.getPropertyValue(configPath, "ETO");
		int intETO = Integer.parseInt(strETO);
		
		String browser =   Utility.getPropertyValue(configPath, "Browser");
		
		if(useGrid.equalsIgnoreCase("Yes"))
		{
			URL url=new URL(gridURL);
			
			if(browser.equalsIgnoreCase("Chrome"))
			{
				ChromeOptions options=new ChromeOptions();
				driver=new RemoteWebDriver(url, options);
			}
			else if(browser.equalsIgnoreCase("Firefox")) 
			{
				FirefoxOptions options=new FirefoxOptions();
				driver=new RemoteWebDriver(url, options);
			}
			else
			{
				Reporter.log("Invalid Browser",true);
			}
			
		}
		else
		{
			
				if(browser.equalsIgnoreCase("Chrome"))
				{
					WebDriverManager.chromedriver().setup();
					driver=new ChromeDriver();
					Reporter.log("Opening Chrome Browser",true);
				}
				else if(browser.equalsIgnoreCase("Firefox")) 
				{
					WebDriverManager.firefoxdriver().setup();
					driver =new FirefoxDriver();
				}
				else
				{
					Reporter.log("Invalid Browser",true);
				}
		}
		driver.manage().window().maximize();
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(intITO));
		wait = new WebDriverWait(driver, Duration.ofSeconds(intETO));
	}
	
	@AfterMethod
	public void closeApp()
	{
		driver.quit();
		Reporter.log("Closing the Browser",true);
	}
}
