package DebuggerBrowser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
 
public class DebuggerManager {
    private static final String CHROME_PATH = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
    private static final int PORT = 9222;
    private static final String USER_DATA_DIR = "C:\\SkybeyoSalesforce";
    static String fileName = "recovery_state.json";
    static WebDriver driver = null;
    
    static String filePath = USER_DATA_DIR + "\\" + fileName;
    
    public static void main(String[] args) throws Exception {
    	Boolean bol = isDebuggerActive();
    	driver = getConnectedDriver();
    	
    	if(bol) {
    		resumeSession(driver, filePath);
    	}
    	
    	
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//    	driver.get("https://demoqa.com/elements");
//    	
//    	driver.manage().window().maximize();
//    	
//    	String originalUrl = driver.getCurrentUrl();
//    	System.out.println("Original URL: " + originalUrl);
//
//    	// 2. Open a new blank tab and automatically switch focus to it
//    	driver.switchTo().newWindow(WindowType.TAB);
//    	
//    	driver.get("https://demoqa.com/nestedframes");
//    	
//    	WebElement frame1 = driver.findElement(By.xpath("//iframe[@id=\"frame1\"]"));
//    	driver.switchTo().frame(frame1);
//    	
//    	WebElement frame2 = driver.findElement(By.xpath("//iframe[@srcdoc=\"<p>Child Iframe</p>\"]"));
//    	driver.switchTo().frame(frame2);
    	
    	SessionId session = ((RemoteWebDriver) driver).getSessionId();
    	System.out.println("Current Session ID: " + session.toString());
    	
    	//-----------------------------------
    	
    	Thread.sleep(2000);

    	
    	System.out.println("----------------------");
    	System.out.println(driver.findElement(By.xpath("//body/p[contains(.,'Child')]")).getText());
    	System.out.println("----------------------");

    	
    	saveCurrentStateToProfile(driver, USER_DATA_DIR);
    	

    	 
	}
    
    public static WebDriver getConnectedDriver() {
    	if (!isDebuggerActive()) {       
    		System.out.println("Debugger not active. Cleaning up stale recovery data..."); 
	    	// 1. Check for recovery_state.json and delete if exists        
	    	cleanupRecoveryFile();             
	    	// 2. Launch new Debugger Browser launchDebuggerChrome();
	    	launchDebuggerChrome();
    	} else {
    		System.out.println("Debugger already active. Connecting to existing session.");
    	}
    	
   
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:" + PORT);
        // This connects to the EXISTING browser session
        return new ChromeDriver(options);
    }
 
	 private static void cleanupRecoveryFile() {  
		 Path path = Paths.get(USER_DATA_DIR, "recovery_state.json");

		 try {
		     // This deletes the file ONLY if it exists. 
		     // If it doesn't exist, it simply returns false without throwing an exception.
		     Files.deleteIfExists(path); 
		 } catch (IOException e) {
		     // This only catches actual "errors" (like the file being locked by Chrome)
		     // rather than the file simply being missing.
		     System.err.println("Could not delete file due to permission/lock issues.");
		 }
	}

    private static boolean isDebuggerActive() {
        try (Socket socket = new Socket("127.0.0.1", PORT)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
 
    private static void launchDebuggerChrome() {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                CHROME_PATH, 
                "--remote-debugging-port=" + PORT, 
                "--user-data-dir=" + USER_DATA_DIR
            );
            pb.start();
            Thread.sleep(3000); // Wait for browser to initialize
        } catch (Exception e) {
            throw new RuntimeException("Failed to launch Chrome: " + e.getMessage());
        }
    }
    
    public static void resumeSession(WebDriver driver, String filePath) throws Exception {
        Path path = Paths.get(filePath);

        // 1. Check if the file exists before attempting to read it
        if (!Files.exists(path)) {
            System.out.println("No recovery_state.json found. Skipping session resumption.");
            return; // Exit the method safely
        }

        // 2. Read JSON file
        String content = new String(Files.readAllBytes(path));
        RecoveryData data = new Gson().fromJson(content, RecoveryData.class);

        if (data == null) {
            System.out.println("Recovery data is empty. Skipping resume.");
            return;
        }

        // 3. Find the correct Tab
        boolean tabFound = false;
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            // Using a try-catch here prevents crashes if a tab closes during the loop
            System.out.println("-----------Title and Url form json file-----------");
            System.out.println(data.url);
            System.out.println(data.title);
            System.out.println("---------------------------------------------");
            try {
                if (driver.getCurrentUrl().contains(data.url) && driver.getTitle().equals(data.title)) {
                    tabFound = true;
                    break;
                }
            } catch (Exception e) {
                continue; 
            }
        }

        if (!tabFound) {
            System.out.println("Could not find the specific tab. Staying on current page.");
            return; 
        }

        // 4. Switch to Iframe path
        driver.switchTo().defaultContent(); 
        if (data.iframePath != null && !data.iframePath.isEmpty()) {
            System.out.println("Restoring Iframe Context...");
            for (String step : data.iframePath) {
                switchToIframe(driver, step);
            }
        }
        System.out.println("Successfully resumed session state.");
    }
 
    private static  void switchToIframe(WebDriver driver, String instruction) {
        String[] parts = instruction.split(":", 2);
        String type = parts[0];
        String value = parts[1];
        System.out.println(type);
        System.out.println(value);
 
        switch (type) {
            case "id":
            	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            	driver.switchTo().frame(value);
            	break;
            case "name":
            	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                driver.switchTo().frame(value);
                break;
            case "index":
            	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                driver.switchTo().frame(Integer.parseInt(value));
                break;
            case "css":
            	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                driver.switchTo().frame(driver.findElement(By.cssSelector(value)));
                break;
        }
    }
    
    
    
    
    
    
    
    //------------------------------------------------------
    //------------------------------------------------------
    
    
    /**
     * Captures current browser state and saves it to recovery_state.json 
     * inside the specified Chrome Profile directory.
     */
    public  static void saveCurrentStateToProfile(WebDriver driver, String chromeProfilePath) {
        try {
            // 1. Collect Data
            String currentUrl = driver.getCurrentUrl();
            String tabTitle = driver.getTitle();
            // This calls the JavaScript Crawler method we developed earlier
            List<String> iframeBreadcrumbs = getIframeBreadcrumbs(driver);
 
            // 2. Create the Data Object
            RecoveryData state = new RecoveryData(currentUrl, tabTitle, iframeBreadcrumbs);
 
            // 3. Define the File Path (Inside the Chrome Profile)
            File profileDir = new File(chromeProfilePath);
            if (!profileDir.exists()) {
                profileDir.mkdirs();
            }
            File recoveryFile = new File(profileDir, "recovery_state.json");
 
            // 4. Write to JSON using GSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter(recoveryFile)) {
                gson.toJson(state, writer);
            }
 
            System.out.println("Success: Recovery state saved to " + recoveryFile.getAbsolutePath());
 
        } catch (Exception e) {
            System.err.println("Failed to save recovery state: " + e.getMessage());
        }
    }
 
    private static List<String> getIframeBreadcrumbs(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String crawlScript = "var path = []; var curr = window; " +
                "while (curr !== window.top) { " +
                "  var el = curr.frameElement; var ident = ''; " +
                "  if (el.id) { ident = 'id:' + el.id; } " +
                "  else if (el.name) { ident = 'name:' + el.name; } " +
                "  else if (el.className) { ident = 'css:iframe.' + el.className.trim().split(/\\s+/).join('.'); } " +
                "  else if (el.getAttribute('src')) { " +
                "    var src = el.getAttribute('src').split('?')[0]; " +
                "    ident = 'css:iframe[src*=\\'' + src + '\\']'; " +
                "  } else { " +
                "    var siblings = el.parentNode.querySelectorAll('iframe'); " +
                "    for (var i = 0; i < siblings.length; i++) { " +
                "      if (siblings[i] === el) { ident = 'index:' + i; break; } " +
                "    } " +
                "  } " +
                "  path.push(ident); curr = curr.parent; " +
                "} return path.reverse();";

        return (List<String>) js.executeScript(crawlScript);
    }
        
    
}
/**
* POJO for JSON Mapping
*/
class RecoveryData {
    String url;
    String title;
    List<String> iframePath;
 
    public RecoveryData(String url, String title, List<String> iframePath) {
        this.url = url;
        this.title = title;
        this.iframePath = iframePath;
    }
}


