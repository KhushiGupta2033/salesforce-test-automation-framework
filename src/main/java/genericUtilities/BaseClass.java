package genericUtilities;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import WebDriverUtilities.WebDriverMethods;
import objectRepository.LoginPage;
import reusableClass.DebuggerLauncher;

/**
 * This class consists of basic configuration annotations of TestNG
 */
public class BaseClass {
	
	protected WebDriver driver;
	PropertyFileUtility fUtil= new PropertyFileUtility(".\\resources\\CommonData.properties");
	LoginPage lp;
	WebDriverMethods webUtil;
	
	
	@BeforeTest
	public void btConfig() throws Exception {
		 String browser = fUtil.readDataFromPropertiesFile("browser");
		    String URL = fUtil.readDataFromPropertiesFile("url");

		    if (browser.equalsIgnoreCase("chrome")) {
		        driver = DebuggerLauncher.getChromeWithDebugger();
		    } else if (browser.equalsIgnoreCase("firefox")) {
		        driver = new FirefoxDriver();
		    } else if (browser.equalsIgnoreCase("edge")) {
		        driver = new EdgeDriver();
		    }

		webUtil= new WebDriverMethods(driver);
		webUtil.getWebDriver();
//		webUtil.maximizeBrowserWindow();
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
