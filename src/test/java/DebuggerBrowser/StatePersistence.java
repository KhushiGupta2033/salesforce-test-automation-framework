package DebuggerBrowser;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
public class StatePersistence {
 
    /**
     * Captures current browser state and saves it to recovery_state.json 
     * inside the specified Chrome Profile directory.
     */
    public void saveCurrentStateToProfile(WebDriver driver, String chromeProfilePath) {
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
 
    private List<String> getIframeBreadcrumbs(WebDriver driver) {
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
 
///**
//* POJO for JSON Mapping
//*/
//class RecoveryData {
//    String url;
//    String title;
//    List<String> iframePath;
// 
//    public RecoveryData(String url, String title, List<String> iframePath) {
//        this.url = url;
//        this.title = title;
//        this.iframePath = iframePath;
//    }
//}
