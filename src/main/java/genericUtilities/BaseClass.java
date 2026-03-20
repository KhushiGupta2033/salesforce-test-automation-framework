package genericUtilities;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import WebDriverUtilities.WebDriverMethods;
import objectRepository.LoginPage;

/**
 * This class consists of basic configuration annotations of TestNG
 */
public class BaseClass {
	
	WebDriver driver;
	PropertyFileUtility fUtil= new PropertyFileUtility(".\\resources\\CommonData.properties");
	LoginPage lp;
	WebDriverMethods webUtil;
	
	
	@BeforeTest
	public void btConfig() throws IOException {
		String Browser =fUtil.readDataFromPropertiesFile("browser");
		String URL =fUtil.readDataFromPropertiesFile("url");
		String path =fUtil.readDataFromPropertiesFile("path");
		
		
		if(Browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--user-data-dir"+path);
			driver = new ChromeDriver(options);
			System.out.println("Chrome Browser Opened");
		}
		else if(Browser.equalsIgnoreCase("firefox")){
			FirefoxOptions options= new FirefoxOptions();
			options.addArguments("--user-data-dir"+path);
			driver=new FirefoxDriver(options);
			System.out.println("FireFox Browser Opened");
		}
		else if(Browser.equalsIgnoreCase("edge")){
			EdgeOptions options= new EdgeOptions();
			options.addArguments("--user-data-dir"+path);
			driver=new EdgeDriver(options);
			System.out.println("Edge Browser Opened");
		}else {
			driver=new ChromeDriver();
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--user-data-dir"+path);
			System.out.println("Default Browser Opened");
		}
		
		
		webUtil= new WebDriverMethods(driver);
		driver=webUtil.getWebDriver();
		webUtil.maximizeBrowserWindow();
		driver.get(URL);

	}
	
	@BeforeMethod
	public void bmLogin() throws IOException {
		String user=fUtil.readDataFromPropertiesFile("username");
		String pass=fUtil.readDataFromPropertiesFile("password");
		lp= new LoginPage(driver);
		lp.logIn(user, pass, webUtil);
		
	}

}
