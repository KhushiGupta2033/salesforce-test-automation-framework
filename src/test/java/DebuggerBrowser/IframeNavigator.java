package DebuggerBrowser;


import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
 
public class IframeNavigator {
 
    public static void main(String[] args) {
        // Setup Chrome Options if you are connecting to a debugger browser
        // For standard execution, we launch a new instance here
        WebDriver driver = new ChromeDriver();
 
        try {
            // 1. Navigate to the URL
            driver.get("https://the-internet.herokuapp.com/nested_frames");
 
            // 2. Maximize window
            driver.manage().window().maximize();
 
            // 3. Switch to 1st frame using the provided XPath
            // Hierarchy: Top Level -> Frame Top
            WebElement topFrame = driver.findElement(By.xpath("//frame[@name='frame-top']"));
            driver.switchTo().frame(topFrame);
            System.out.println("Switched to Top Frame");
 
            // 4. Switch to 2nd nested frame using the provided XPath
            // Hierarchy: Frame Top -> Frame Middle
            WebElement middleFrame = driver.findElement(By.xpath("//frame[@name='frame-middle']"));
            driver.switchTo().frame(middleFrame);
            System.out.println("Switched to Middle Frame");
 
            // 5. Click or get text from the element in the middle frame
            WebElement content = driver.findElement(By.id("content"));
            String text = content.getText();
            System.out.println("Content found: " + text);
            // Perform the action (Clicking a div usually requires a click or verification)
            content.click();
 
 
            Thread.sleep(4000);
            
            handleFailureAndSaveState(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // driver.quit(); // Keep open if using for debugging
        }
    }
    
 // Inside your failure handling method
    public static void handleFailureAndSaveState(WebDriver driver) {
    try {
        JavascriptExecutor js = (JavascriptExecutor) driver;               
        // 1. Define the script string (the one you provided)      
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

        // 2. Execute and get the breadcrumbs
        List<String> iframePath = (List<String>) js.executeScript(crawlScript);
        
        iframePath.forEach(System.out::println);        
        // 3. Capture Tab Metadata
        String currentUrl = driver.getCurrentUrl();
        String tabTitle = driver.getTitle();

//        // 4. Store to a local file (recovery_state.json)
//        RecoveryData data = new RecoveryData(currentUrl, tabTitle, iframePath);
//        String json = new Gson().toJson(data);
//        Files.write(Paths.get("recovery_state.json"), json.getBytes());
//        
//        System.out.println("State saved successfully. Path: " + iframePath);

    } catch (Exception e) {
        System.out.println("Could not capture iframe path. You might be at the Root level.");
    }
}
 
}
