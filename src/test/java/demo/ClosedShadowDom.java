package demo;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class ClosedShadowDom {
    public static void forceAllShadowRootsOpen(WebDriver driver) {

        // Applicable only in Chromium-based browsers
        if (driver instanceof org.openqa.selenium.chromium.ChromiumDriver) {

            String patchJS =
                    "(function() {" +
                    "  const original = Element.prototype.attachShadow;" +
                    "  Element.prototype.attachShadow = function(init) {" +
                    "    init = Object.assign({}, init, { mode: 'open' });" +
                    "    const shadow = original.call(this, init);" +
                    "    Object.defineProperty(this, 'shadowRoot', { value: shadow });" +
                    "    return shadow;" +
                    "  };" +
                    "})();";

            Map<String, Object> params = new HashMap<>();
            params.put("source", patchJS);

            ((org.openqa.selenium.chromium.ChromiumDriver) driver)
                    .executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", params);
            return;
        }
        System.out.println("forceAllShadowRootsOpen is NOT supported in this browser. "
                + "Closed shadow DOM cannot be opened by WebDriver.");
    }
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(4000);
        forceAllShadowRootsOpen(driver);
        driver.get("https://jec.fish/demo/shadow-open-close?utm_source=chatgpt.com");
        
    }
}